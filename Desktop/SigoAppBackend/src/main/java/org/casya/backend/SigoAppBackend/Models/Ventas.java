package org.casya.backend.SigoAppBackend.Models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.List;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "ventas")
@SequenceGenerator(name = "ventas_sequence", sequenceName = "ventas_sequence", initialValue = 1000)
public class Ventas {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ventas_sequence")
    private long id;
    @NotNull
    private String strFechaVenta;
    @NotNull
    private List<Long> articulos;
    @NotNull
    private long lngSubTotal;
    @NotNull
    private long lngDescuento;
    @NotNull
    private long lngTotalFinal;
    @NotNull
    private long lngPagoEfectivo;
    @NotNull
    private long lngPagoTarjeta;
    @NotNull
    private Long noCaja;
    private Long idUsuario;
    @Embedded
    private PagoConTarjeta pagoConTarjeta;
}
  