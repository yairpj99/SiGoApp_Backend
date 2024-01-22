package org.casya.backend.SigoAppBackend.Mapper;
import org.casya.backend.SigoAppBackend.Models.Ventas;
import org.casya.backend.SigoAppBackend.dtos.CreateVentasDto;
import org.casya.backend.SigoAppBackend.dtos.VentasDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface VentasMapper {
    VentasDto toDto(Ventas model);

    @Mapping(target = "id", ignore = true)
    Ventas toModel(CreateVentasDto dto);
}
