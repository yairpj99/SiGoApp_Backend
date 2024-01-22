package org.casya.backend.SigoAppBackend.Controllers;

import java.util.List;
import java.util.Collections;
import org.casya.backend.SigoAppBackend.Service.InventarioService;
import org.casya.backend.SigoAppBackend.dtos.CreateArticuloInventario;
import org.casya.backend.SigoAppBackend.dtos.InventarioDto;
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
@RequestMapping("inventario")
public class InventarioController {
    @Autowired
    private InventarioService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventarioDto>findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public InventarioDto findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping("/categoria/{strCategoria}")
    @ResponseStatus(HttpStatus.OK)
    public List<InventarioDto> findByStrCategoria(@PathVariable String strCategoria){
        return service.findByStrCategoria(strCategoria);
    }

    @GetMapping("/marca/{strMarca}")
    @ResponseStatus(HttpStatus.OK)
    public List<InventarioDto> findByStrMarca(@PathVariable String strMarca) {
        try {
            return service.findByStrMarca(strMarca);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();  // Retorna una lista vacía en caso de error.
        }
    }

    

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InventarioDto save(@Valid @RequestBody CreateArticuloInventario data){
        return service.save(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean>deleteInventario(@PathVariable Long id){
        try {
            boolean deleted = service.deleteById(id);
            if (deleted) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    @PutMapping("/agregarUno/{sku}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public InventarioDto updateCantidad(@PathVariable Long sku){
        return service.editarCantidad(sku);
    }
    
}
