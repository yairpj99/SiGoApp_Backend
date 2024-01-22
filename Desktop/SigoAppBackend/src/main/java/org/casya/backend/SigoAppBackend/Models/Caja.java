package org.casya.backend.SigoAppBackend.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="resgistrosDeCaja")
public class Caja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noCaja;
    private long idUser;
    private long lngFondoDeCaja;
    private long lngVentas; 
    private long lngArticulosVendidos;
    private long lngEfectivoEnCaja; 
    private long lngTotalCobrado; 
    private long lngEfectivo; 
    private long lngTarjeta;
    private String strFechaApertura;
    private String strFechaCierre;
    private boolean status;
}
