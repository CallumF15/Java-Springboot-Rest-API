package com.company.business.Mappers;

import org.mapstruct.Mapper;

//Create a class at compile time that converts between my objects (e.g. Entity ↔ DTO)
//Register the generated implementation as a Spring-managed bean
//@Mapper
public interface BaseMapper<Entity, Dto> {
    Dto toDto(Entity user);

    Entity toEntity(Dto dto);
}
