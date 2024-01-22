package org.casya.backend.SigoAppBackend.Repository;

import org.casya.backend.SigoAppBackend.Models.Retiros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RetirosRepository extends JpaRepository<Retiros, Long> {
    List<Retiros> findBystrFecha(String strFecha);
    List<Retiros> findByNoCaja(Long noCaja);
}
