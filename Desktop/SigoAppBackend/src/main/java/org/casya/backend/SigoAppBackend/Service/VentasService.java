package org.casya.backend.SigoAppBackend.Service;

import org.casya.backend.SigoAppBackend.Configuration.VentaException;
import org.casya.backend.SigoAppBackend.Mapper.VentasMapper;
import org.casya.backend.SigoAppBackend.Models.Caja;
import org.casya.backend.SigoAppBackend.Models.Inventario;
import java.util.Optional;
import org.casya.backend.SigoAppBackend.Models.Ventas;
import org.casya.backend.SigoAppBackend.Repository.CajaRepository;
import org.casya.backend.SigoAppBackend.Repository.InventarioRepository;
import org.casya.backend.SigoAppBackend.Repository.VentasRepository;
import org.casya.backend.SigoAppBackend.dtos.CreateVentasDto;
import org.casya.backend.SigoAppBackend.dtos.VentasDto;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class VentasService {

    @Autowired
    private VentasRepository repository;

    @Autowired 
    InventarioRepository inventarioRepository;

    @Autowired
    CajaRepository cajaRepository;

    @Autowired
    private VentasMapper mapper;

    public List<VentasDto>findAll(){
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public VentasDto save(CreateVentasDto data) {
    Map<Long, Long> cantidadPorArticulo = data.getArticulos().stream()
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

    for (Map.Entry<Long, Long> entry : cantidadPorArticulo.entrySet()) {
        Long articuloId = entry.getKey();
        Long cantidadVenta = entry.getValue();

        Inventario inventario = inventarioRepository.findById(articuloId)
                .orElseThrow(() -> new VentaException("El SKU " + articuloId + " no ha sido encontrado"));

        long cantidadDisponible = inventario.getLngCantidad();

        if (cantidadVenta > cantidadDisponible) {
            throw new VentaException("No cuenta con la cantidad suficiente en el inventario para la venta.");
        }
    }

    for (Map.Entry<Long, Long> entry : cantidadPorArticulo.entrySet()) {
        Long articuloId = entry.getKey();
        Long cantidadVenta = entry.getValue();

        Inventario inventario = inventarioRepository.findById(articuloId)
                .orElseThrow(() -> new VentaException("El artículo con SKU " + articuloId + " no ha sido encontrado"));

        long nuevaCantidad = inventario.getLngCantidad() - cantidadVenta;
        inventario.setLngCantidad(nuevaCantidad);
        inventarioRepository.save(inventario); 
        
        updateCajaCantidadArticulos(data.getNoCaja(), cantidadVenta);
    }

    updateCaja(data.getNoCaja(), data);

    Ventas entity = mapper.toModel(data);
    entity = repository.save(entity);
    return mapper.toDto(entity);

    

}

private void updateCajaCantidadArticulos(Long noCaja, Long cantidadVenta) {
    Caja caja = cajaRepository.findById(noCaja)
            .orElseThrow(() -> new VentaException("Error al realizar la venta"));

    Long nuevaCantidadDeArticulosEnCaja = caja.getLngArticulosVendidos() + cantidadVenta;
    caja.setLngArticulosVendidos(nuevaCantidadDeArticulosEnCaja);
    cajaRepository.save(caja);
}

private void updateCaja(Long noCaja, CreateVentasDto data){
    Caja caja = cajaRepository.findById(noCaja).orElseThrow(() -> new VentaException("Caja con ID " + noCaja + " no encontrada"));

    Long nuevoTotalVendido = caja.getLngVentas() + data.getLngTotalFinal();
    caja.setLngVentas(nuevoTotalVendido);
    
    Long nuevoTarjeta = caja.getLngTarjeta() + data.getLngPagoTarjeta();
    caja.setLngTarjeta(nuevoTarjeta);

    Long nuevoEfectivo = caja.getLngEfectivo() + data.getLngPagoEfectivo();
    caja.setLngEfectivo(nuevoEfectivo);

    Long nuevoTotalCobrado = caja.getLngTotalCobrado() + data.getLngTotalFinal();
    caja.setLngTotalCobrado(nuevoTotalCobrado);

    Long cambio = ((data.getLngTotalFinal() - data.getLngPagoTarjeta() - data.getLngPagoEfectivo())*-1);

    Long nuevoEfectivoEnCaja = caja.getLngEfectivoEnCaja() + data.getLngPagoEfectivo() - cambio;
    caja.setLngEfectivoEnCaja(nuevoEfectivoEnCaja);

    cajaRepository.save(caja);
}

public boolean deleteById(Long id){
    try{
        repository.deleteById(id);
        return true;
    }catch(EmptyResultDataAccessException e){
        return false;
    }catch(Exception e){
        return false;
    }
}


public List<VentasDto> findByStrFechaVenta(String strFechaDeVenta) {
    List<Ventas> ventasList = repository.findByStrFechaVenta(strFechaDeVenta);

    if (!ventasList.isEmpty()) {
        return ventasList.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    } else {
        return Collections.emptyList();
    }

}

public VentasDto findById(Long id){
    Optional<Ventas> optional = repository.findById(id);
    
    if(optional.isPresent()){
        Ventas venta = optional.get();
        VentasDto dto = mapper.toDto(venta);
        return dto; 
    }else{
        return null;
    }

}

}


