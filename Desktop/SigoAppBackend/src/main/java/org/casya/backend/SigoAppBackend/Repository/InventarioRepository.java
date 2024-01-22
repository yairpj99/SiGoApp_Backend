package org.casya.backend.SigoAppBackend.Repository;

import java.util.List;

import org.casya.backend.SigoAppBackend.Models.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long>{
 List<Inventario> findByStrCategoria(String  strCategoria);
 List<Inventario> findByStrMarca(String strMarca);
    
}