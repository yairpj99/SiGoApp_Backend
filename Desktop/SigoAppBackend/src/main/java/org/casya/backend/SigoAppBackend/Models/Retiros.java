package org.casya.backend.SigoAppBackend.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="retiroRegistros")
public class Retiros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String strFecha;
    @NotNull
    private long noCaja;
    private long lngModenasUno;
    private long lngModenasDos;
    private long lngModenasCinco;
    private long lngModenasDiez;
    private long lngModenasVenite;
    private long lngBilletesCincuenta;
    private long lngBilletesCien;
    private long lngBilletesDoscientos;
    private long lngBilletesQuinientos;
    private long lngBilletesMil;
    @Min(1)
    private long lngMontoRetiro;
}
