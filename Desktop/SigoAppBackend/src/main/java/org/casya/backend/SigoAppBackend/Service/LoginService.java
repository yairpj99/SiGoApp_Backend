package org.casya.backend.SigoAppBackend.Service;

import org.casya.backend.SigoAppBackend.Models.User;
import org.casya.backend.SigoAppBackend.Repository.UserRepository;
import org.casya.backend.SigoAppBackend.dtos.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired 
    private UserRepository repository;

    public User login(LoginDto loginDto) {
        // Buscar el usuario por ID
        User user = repository.findById(loginDto.getId()).orElse(null);

        if (user != null && user.getPassword().equals(loginDto.getPassword())) {
            return user; // Devolver el objeto User si la autenticación es exitosa
        } else {
            return null; // Devolver null si la autenticación falla
        }
    }


    
}
