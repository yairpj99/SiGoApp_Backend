package org.casya.backend.SigoAppBackend.Mapper;

import org.casya.backend.SigoAppBackend.Models.Caja;
import org.casya.backend.SigoAppBackend.dtos.AperturaCaja;
import org.casya.backend.SigoAppBackend.dtos.CajaDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CajaMapper {
    CajaDto toDto(Caja model);
    
    @Mapping(target = "noCaja", ignore = true)
    @Mapping(target = "strFechaApertura", ignore = true)
    @Mapping(target = "strFechaCierre", ignore = true)
    Caja toModel(AperturaCaja dto);
}