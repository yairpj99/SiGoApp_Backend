package org.casya.backend.SigoAppBackend.Mapper;

import org.casya.backend.SigoAppBackend.Models.Retiros;
import org.casya.backend.SigoAppBackend.dtos.CreateRetiroDto;
import org.casya.backend.SigoAppBackend.dtos.RetirosDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface RetirosMapper {
    RetirosDto toDto(Retiros model);

    @Mapping(target = "id", ignore = true)
    Retiros toModel(CreateRetiroDto dto);
}
