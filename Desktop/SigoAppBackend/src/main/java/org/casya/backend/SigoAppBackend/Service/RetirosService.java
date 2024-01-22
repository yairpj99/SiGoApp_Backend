package org.casya.backend.SigoAppBackend.Service;

import org.casya.backend.SigoAppBackend.Configuration.CajaException;
import org.casya.backend.SigoAppBackend.Mapper.RetirosMapper;
import org.casya.backend.SigoAppBackend.Models.Caja;
import org.casya.backend.SigoAppBackend.Models.Inventario;
import org.casya.backend.SigoAppBackend.Models.Retiros;
import org.casya.backend.SigoAppBackend.Repository.CajaRepository;
import org.casya.backend.SigoAppBackend.Repository.RetirosRepository;
import org.casya.backend.SigoAppBackend.Responses.ErrorResponse;
import org.casya.backend.SigoAppBackend.dtos.CreateRetiroDto;
import org.casya.backend.SigoAppBackend.dtos.InventarioDto;
import org.casya.backend.SigoAppBackend.dtos.RetirosDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class RetirosService {

    @Autowired
    private RetirosRepository repository;

    @Autowired
    private CajaRepository cajaRepository;

    @Autowired 
    private RetirosMapper mapper;

    private static final Logger log = LoggerFactory.getLogger(RetirosService.class);


    public List<RetirosDto> findAll() {
        try {
            return repository.findAll().stream().map(mapper::toDto).toList();
        } catch (Exception e) {
            log.error("Error al obtener todos los retiros", e);
            throw new RuntimeException("Error al obtener todos los retiros", e);
        }
    }

    public List<RetirosDto> findBystrFecha(String strFecha){
        try{
            return repository.findBystrFecha(strFecha).stream().map(mapper::toDto).toList();
        }catch(Exception e){
            log.error("Error al obtener retiros con la referencia ingresada.", e);
            throw new RuntimeException("Error al obtener retiros con la referencia ingresada.");
        }
    }
    
    public void newRetiro(CreateRetiroDto data) {
            try {
                updateCaja(data);
                Retiros entity = mapper.toModel(data);
                entity = repository.save(entity);
            } catch (CajaException e) {
                throw e; // Propaga la excepción CajaException
            }
        }   

    public void updateCaja(CreateRetiroDto data) {
        Long montoRetiro = data.getLngMontoRetiro();
        Long noCaja = data.getNoCaja();
    
        Caja caja = cajaRepository.findById(noCaja)
                .orElseThrow(() -> new CajaException("Caja con ID " + noCaja + " no encontrada"));
    
        Long efectivoActualEnCaja = caja.getLngEfectivoEnCaja();
        if (montoRetiro <= efectivoActualEnCaja) {
            Long nuevoEfectivoEnCaja = caja.getLngEfectivoEnCaja() - montoRetiro;
            caja.setLngEfectivoEnCaja(nuevoEfectivoEnCaja);
            cajaRepository.save(caja);
        } else {
            throw new CajaException("La caja " + noCaja + " solamente cuenta con: $ " + efectivoActualEnCaja);
        }
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

    public RetirosDto findById(Long id){
        Optional<Retiros> optional = repository.findById(id);
        if (optional.isPresent()) {
            Retiros retiro = optional.get();
            RetirosDto dto = mapper.toDto(retiro);
            return dto;
        } else {
            return null;
        }
    }

    public List<RetirosDto> findByLngNoCaja(Long noCaja) {
        List<Retiros> resultados = repository.findByNoCaja(noCaja);
        
        if (resultados.isEmpty()) {
            log.error("No se encontraron resultados con la referencia ingresada");
            throw new RuntimeException("No se encontraron resultados con la referencia ingresada");
        }
        return resultados.stream().map(mapper::toDto).toList();
    }
}
