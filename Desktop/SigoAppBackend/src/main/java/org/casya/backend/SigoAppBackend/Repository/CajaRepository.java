package org.casya.backend.SigoAppBackend.Repository;

import org.casya.backend.SigoAppBackend.Models.Caja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CajaRepository extends JpaRepository<Caja, Long> {
    Caja findCajaByIdUser(Long idUser);
}
