package org.casya.backend.SigoAppBackend.dtos;

import lombok.Data;

@Data
public class LoginDto {
    private long id;
    private String password;
}
