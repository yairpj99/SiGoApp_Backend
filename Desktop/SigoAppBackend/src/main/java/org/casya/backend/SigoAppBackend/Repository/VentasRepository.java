package org.casya.backend.SigoAppBackend.Repository;

import org.casya.backend.SigoAppBackend.Models.Ventas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VentasRepository extends JpaRepository<Ventas, Long> {
    List<Ventas> findByStrFechaVenta(String strFechaVenta);
}

