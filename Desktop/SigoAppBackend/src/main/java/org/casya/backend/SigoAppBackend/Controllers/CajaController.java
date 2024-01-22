package org.casya.backend.SigoAppBackend.Controllers;

import java.util.List;

import org.casya.backend.SigoAppBackend.Configuration.CajaException;
import org.casya.backend.SigoAppBackend.Configuration.ExceptionAdvice;
import org.casya.backend.SigoAppBackend.Service.CajaService;
import org.casya.backend.SigoAppBackend.dtos.AperturaCaja;
import org.casya.backend.SigoAppBackend.dtos.CajaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("caja")
public class CajaController {

    @Autowired
    private CajaService service;

    @GetMapping("/cajas")
    @ResponseStatus(HttpStatus.OK)
    public List<CajaDto>findAll(){
        return service.findAll();
    }

    @GetMapping("/consulta/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public CajaDto consulta(@PathVariable Long idUser){
        return service.consultaCaja(idUser);
    }

    @GetMapping("/{noCaja}")
    @ResponseStatus(HttpStatus.OK)
    public CajaDto findByNoCaja(@PathVariable Long noCaja){
        return service.findById(noCaja);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CajaDto save(@Valid @RequestBody AperturaCaja data){
        return service.aperturaCaja(data);
    }

    @PutMapping("/cerrarCaja/{noCaja}/{strFechaCierre}")
    public ResponseEntity<String> cerrarCaja(@PathVariable Long noCaja, @PathVariable String strFechaCierre) {
        try {
            return service.cerrarCaja(noCaja, strFechaCierre);
        } catch (CajaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{noCaja}")
    public ResponseEntity<String> eliminarCaja(@PathVariable Long noCaja){
        try{
            return service.eliminarCaja(noCaja);
        }catch(CajaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    
    
}

