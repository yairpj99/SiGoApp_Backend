package org.casya.backend.SigoAppBackend.Repository;

import org.casya.backend.SigoAppBackend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
