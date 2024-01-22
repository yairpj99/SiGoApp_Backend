package org.casya.backend.SigoAppBackend.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name="inventarios")
@SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", initialValue = 5000)
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private long id;
    @NotNull
    private String strDescripcion;
    @NotNull
    private String strMarca;
    @NotNull
    private String strCategoria;
    @NotNull
    private String strTalla;
    @NotNull
    private long lngPrecioUnitario;
    @NotNull
    private long lngDescuentoDirecto;
    @NotNull
    private long lngImporte;
    @NotNull
    private long lngCantidad;
    @NotNull
    private String strAlmacen;

}
