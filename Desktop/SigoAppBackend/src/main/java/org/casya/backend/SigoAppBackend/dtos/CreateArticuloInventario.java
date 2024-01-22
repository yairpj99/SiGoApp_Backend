package org.casya.backend.SigoAppBackend.dtos;

import lombok.Value;

@Value
public class CreateArticuloInventario {
    private String strDescripcion;
    private String strMarca;
    private String strCategoria;
    private String strTalla;
    private long lngPrecioUnitario;
    private long lngDescuentoDirecto;
    private long lngCantidad;
    private String strAlmacen;
}
