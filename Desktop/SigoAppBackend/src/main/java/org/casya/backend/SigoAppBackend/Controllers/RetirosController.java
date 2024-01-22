package org.casya.backend.SigoAppBackend.Controllers;

import org.apache.catalina.connector.Response;
import org.casya.backend.SigoAppBackend.Configuration.CajaException;
import org.casya.backend.SigoAppBackend.Responses.ErrorResponse;
import org.casya.backend.SigoAppBackend.Service.RetirosService;
import org.casya.backend.SigoAppBackend.dtos.CreateRetiroDto;
import org.casya.backend.SigoAppBackend.dtos.RetirosDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/retiros")
public class RetirosController {
    
    @Autowired
    private RetirosService service;

    private static final Logger log = LoggerFactory.getLogger(RetirosController.class);
    
    @GetMapping
    public ResponseEntity<List<RetirosDto>> findAll() {
        try {
            List<RetirosDto> retiros = service.findAll();
            return new ResponseEntity<>(retiros, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error("Error al obtener todos los retiros", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fecha/{strFecha}")
    public ResponseEntity<List<RetirosDto>> findBystrFecha(@PathVariable String strFecha){
        try{
            List<RetirosDto> retiros = service.findBystrFecha(strFecha);
            return new ResponseEntity<>(retiros, HttpStatus.OK);
        }catch(RuntimeException e){
            log.error("Error al obtener los retiros con la referencia ingresada.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/nuevoRetiro")
    public ResponseEntity<Object> newRetiro(@Valid @RequestBody CreateRetiroDto data) {
        try {
            service.newRetiro(data);
            return ResponseEntity.ok("Retiro realizado exitosamente");
        } catch (CajaException e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMessage, null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al realizar el retiro");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean>deleteRetiroById(@PathVariable Long id){
        try{
            boolean delete = service.deleteById(id);
            if(delete){
                return ResponseEntity.ok(true);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RetirosDto findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping("/noCaja/{noCaja}")
    public ResponseEntity<List<RetirosDto>> findByLngNoCaja(@PathVariable Long noCaja) {
        try {
            List<RetirosDto> retiros = service.findByLngNoCaja(noCaja);
            if (retiros.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(retiros, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.error("Error al obtener los retiros con la referencia ingresada.", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
