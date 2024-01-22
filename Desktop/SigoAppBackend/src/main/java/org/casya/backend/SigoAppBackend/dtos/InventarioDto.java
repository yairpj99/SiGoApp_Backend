package org.casya.backend.SigoAppBackend.dtos;

import lombok.Data;

@Data
public class InventarioDto {
    private long id;
    private String strDescripcion;
    private String strMarca;
    private String strCategoria;
    private String strTalla;
    private long lngPrecioUnitario;
    private long lngDescuentoDirecto;
    private long lngImporte;
    private long lngCantidad;
    private String strAlmacen;
}
