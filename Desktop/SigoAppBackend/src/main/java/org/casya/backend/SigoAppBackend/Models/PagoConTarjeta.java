package org.casya.backend.SigoAppBackend.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PagoConTarjeta {
    private String strFolio;
    private String strDigitos;
}
