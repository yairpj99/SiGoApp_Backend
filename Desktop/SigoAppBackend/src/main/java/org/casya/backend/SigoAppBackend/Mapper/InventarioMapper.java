package org.casya.backend.SigoAppBackend.Mapper;

import org.casya.backend.SigoAppBackend.Models.Inventario;
import org.casya.backend.SigoAppBackend.dtos.CreateArticuloInventario;
import org.casya.backend.SigoAppBackend.dtos.InventarioDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface InventarioMapper {
    InventarioDto toDto(Inventario model);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lngImporte", ignore = true)
    Inventario toModel(CreateArticuloInventario dto);
}
