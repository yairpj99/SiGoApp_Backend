package org.casya.backend.SigoAppBackend.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RetirosDto {
    private long id;
    private String strFecha;
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
    private long lngMontoRetiro;
}
