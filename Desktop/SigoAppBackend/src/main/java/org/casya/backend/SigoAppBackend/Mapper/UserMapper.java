package org.casya.backend.SigoAppBackend.Mapper;

import org.casya.backend.SigoAppBackend.Models.User;
import org.casya.backend.SigoAppBackend.dtos.CreateUserDto;
import org.casya.backend.SigoAppBackend.dtos.UserDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {
    UserDto toDto(User model); 
    
    @Mapping(target = "id", ignore = true)
    User toModel(CreateUserDto dto);
}