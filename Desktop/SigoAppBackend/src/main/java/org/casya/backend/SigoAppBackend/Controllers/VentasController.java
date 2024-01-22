package org.casya.backend.SigoAppBackend.Controllers;

import org.casya.backend.SigoAppBackend.Service.VentasService;
import org.casya.backend.SigoAppBackend.dtos.CreateVentasDto;
import org.casya.backend.SigoAppBackend.dtos.VentasDto;
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

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("ventas")
public class VentasController {

    @Autowired
    private VentasService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<VentasDto>findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VentasDto findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping("/fecha/{strFechaDeVenta}")
    @ResponseStatus(HttpStatus.OK)
    public List<VentasDto> findByStrFechaDeVenta(@PathVariable String strFechaDeVenta) {
        return service.findByStrFechaVenta(strFechaDeVenta);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VentasDto save(@Valid @RequestBody CreateVentasDto data){
        return service.save(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        try{
            boolean deleted = service.deleteById(id);
            if(deleted){
                return ResponseEntity.ok(true);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    
}
