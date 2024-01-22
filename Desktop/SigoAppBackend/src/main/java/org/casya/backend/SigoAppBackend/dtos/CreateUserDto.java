package org.casya.backend.SigoAppBackend.dtos;

import org.casya.backend.SigoAppBackend.Models.DataPerson;

import lombok.Data;

@Data
public class CreateUserDto {
    private String password;
    private DataPerson cData;
}
