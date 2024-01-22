package org.casya.backend.SigoAppBackend.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.casya.backend.SigoAppBackend.Configuration.InventarioException;
import org.casya.backend.SigoAppBackend.Mapper.InventarioMapper;
import org.casya.backend.SigoAppBackend.Models.Inventario;
import org.casya.backend.SigoAppBackend.Repository.InventarioRepository;
import org.casya.backend.SigoAppBackend.dtos.CreateArticuloInventario;
import org.casya.backend.SigoAppBackend.dtos.InventarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class InventarioService {
    @Autowired
    private InventarioRepository repository;

    @Autowired
    private InventarioMapper mapper;

    public List<InventarioDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public InventarioDto save(CreateArticuloInventario data) {
        // Mapea los datos al modelo de entidad
        Inventario entity = mapper.toModel(data);

        // Guarda la entidad en la base de datos
        entity = repository.save(entity);

        // Calcula el precio unitario restando el precio - descuento
        Long lngImporte = entity.getLngPrecioUnitario() - entity.getLngDescuentoDirecto();
        entity.setLngImporte(lngImporte);

        // Actualiza la entidad en la base de datos con el importe calculado
        entity = repository.save(entity);

        // Mapea la entidad actualizada al DTO y devuélvela
        return mapper.toDto(entity);
    }

    public boolean deleteById(Long id) {
        try {
            repository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public InventarioDto findById(Long id) {
        Optional<Inventario> optionalInventario = repository.findById(id);
        if (optionalInventario.isPresent()) {
            Inventario inventario = optionalInventario.get();
            InventarioDto inventarioDto = mapper.toDto(inventario);
            return inventarioDto;
        } else {
            return null;
        }
    }

    public List<InventarioDto> findByStrCategoria(String strCategoria) {
        List<Inventario> inventarioList = repository.findByStrCategoria(strCategoria);
        if (!inventarioList.isEmpty()) {
            return inventarioList.stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public List<InventarioDto> findByStrMarca(String strMarca) {
        List<Inventario> ventasList = repository.findByStrMarca(strMarca);

        if (!ventasList.isEmpty()) {
            return ventasList.stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public InventarioDto editarCantidad(Long sku){
        Inventario inventario = repository.findById(sku).orElseThrow(()->new InventarioException("No se encontro el SKU: "+sku));

        Long nuevaCantidad = inventario.getLngCantidad() + 1;
        inventario.setLngCantidad(nuevaCantidad);
        repository.save(inventario);

        return mapper.toDto(inventario);
    }

}
