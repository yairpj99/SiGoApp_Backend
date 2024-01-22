package org.casya.backend.SigoAppBackend.Controllers;

import org.casya.backend.SigoAppBackend.Models.User;
import org.casya.backend.SigoAppBackend.Service.LoginService;
import org.casya.backend.SigoAppBackend.dtos.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("login")
public class LoginApp {

    @Autowired
    private LoginService service;

    @PostMapping
    public ResponseEntity<User> login(@RequestBody LoginDto loginDto) {
        User authenticatedUser = service.login(loginDto);

        if (authenticatedUser != null) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }
}
