package org.casya.backend.SigoAppBackend.dtos;

import lombok.Data;
import java.util.List;

import org.casya.backend.SigoAppBackend.Models.PagoConTarjeta;
@Data
public class VentasDto {
    private Long id;
    private String strFechaVenta;
    private List <Long> articulos;
    private long lngSubTotal;
    private long lngDescuento;
    private long lngTotalFinal;
    private long lngPagoEfectivo;
    private long lngPagoTarjeta;
    private Long noCaja;
    private Long idUsuario;
    private PagoConTarjeta pagoConTarjeta;
}