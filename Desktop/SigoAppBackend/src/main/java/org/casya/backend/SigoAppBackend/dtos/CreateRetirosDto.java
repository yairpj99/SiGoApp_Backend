package org.casya.backend.SigoAppBackend.dtos;

import org.casya.backend.SigoAppBackend.Models.DataPerson;

import lombok.Data;

@Data
public class CreateRetirosDto {
    private String strFecha;
    private Long noCaja;
    private Long lnhMonedas1;
    private Long lnhMonedas2;
    private Long lnhMonedas5;
    private Long lnhMonedas10;
    private Long lnhMonedas20;
    private Long lngBilletes20;
    private Long lngBilletes50;
    private Long lngBilletes100;
    private Long lngBilletes200;
    private Long lngBilletes500;
    private Long lngBilletes1000;
    private Long lngMontoRetiro;

}
