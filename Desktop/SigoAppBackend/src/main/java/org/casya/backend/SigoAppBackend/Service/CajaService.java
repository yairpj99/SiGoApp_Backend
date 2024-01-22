package org.casya.backend.SigoAppBackend.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.casya.backend.SigoAppBackend.Configuration.CajaException;
import org.casya.backend.SigoAppBackend.Mapper.CajaMapper;
import org.casya.backend.SigoAppBackend.Models.Caja;
import org.casya.backend.SigoAppBackend.Repository.CajaRepository;
import org.casya.backend.SigoAppBackend.dtos.AperturaCaja;
import org.casya.backend.SigoAppBackend.dtos.CajaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.access.MBeanClientInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
public class CajaService {

    @Autowired
    private CajaRepository repository;

    @Autowired
    private CajaMapper mapper;

    public List<CajaDto> findAll(){
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public CajaDto consultaCaja(Long idUser) {
        Caja optionalCaja = repository.findCajaByIdUser(idUser);
        
        if (optionalCaja != null) {
            CajaDto cajaDto = mapper.toDto(optionalCaja);
            return cajaDto;
        } else {
            return null;
        }
    }
    
    public CajaDto findById(Long noCaja){
        Optional<Caja> optional = repository.findById(noCaja);
        if(optional.isPresent()){
            Caja caja = optional.get();
            CajaDto cajaDto = mapper.toDto(caja);
            return cajaDto;
        }else{
            return null;
        }
    }

   public CajaDto aperturaCaja(AperturaCaja data) {
        Caja entity = mapper.toModel(data);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = LocalDateTime.now().format(formatter);
        entity.setStrFechaApertura(formattedDateTime);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public ResponseEntity<String> cerrarCaja(Long noCaja, String strFechaCierre) {
        Optional<Caja> optional = repository.findById(noCaja);
        if (optional.isPresent()) {
            Caja caja = optional.get();
            if(caja.getLngEfectivoEnCaja()==0){
                caja.setStatus(false);
                caja.setStrFechaCierre(strFechaCierre);
                repository.save(caja);      
                return ResponseEntity.ok("La caja ha sido cerrada");
            }else{
                throw new CajaException("La caja "+noCaja+" cuenta con efectivo");
            }
        } else {
            throw new CajaException("No se encontró la caja con el número: " + noCaja);
        }
    }

    public ResponseEntity<String> eliminarCaja(Long noCaja){
        Optional<Caja> optional = repository.findById(noCaja);
        if(!optional.isPresent()){throw new CajaException("No se encontró la caja con el número: " + noCaja);}
        Caja caja = optional.get();
        try {
            if(caja.isStatus()){throw new CajaException("La caja aun se encuentra abierta.");}else{
            repository.deleteById(noCaja);
            return ResponseEntity.ok("Caja eliminada exitosamente");
            }
        } catch (EmptyResultDataAccessException e) {
            throw new CajaException("No se encontró la caja con el número: " + noCaja);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }


}
