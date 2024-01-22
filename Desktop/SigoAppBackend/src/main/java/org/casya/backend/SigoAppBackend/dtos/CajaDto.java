package org.casya.backend.SigoAppBackend.dtos;


import lombok.Data;

@Data
public class CajaDto {
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
