package org.casya.backend.SigoAppBackend.dtos;


import lombok.Value;

@Value
public class AperturaCaja {
    private long idUser;
    private long lngFondoDeCaja;
    private long lngVentas;
    private long lngArticulosVendidos;
    private long lngEfectivoEnCaja;
    private long lngTotalCobrado;
    private long lngEfectivo;
    private long lngTarjeta;
    private boolean status;
}
