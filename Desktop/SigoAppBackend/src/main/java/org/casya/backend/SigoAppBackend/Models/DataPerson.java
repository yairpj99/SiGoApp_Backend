package org.casya.backend.SigoAppBackend.Models;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class DataPerson {
    private String strNombre;
    private String strPaterno;
    private String strMaterno;
    private String strTelefono;
    private String strFechaDeNacimiento;
    private String strAcceso;
}
