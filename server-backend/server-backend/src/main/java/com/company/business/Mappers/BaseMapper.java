package com.company.business.Mappers;

import com.company.business.dto.Business.response.AddressResponseDTO;
import com.company.business.models.business.Address;
import org.mapstruct.Mapper;

//Create a class at compile time that converts between my objects (e.g. Entity ↔ DTO)
//Register the generated implementation as a Spring-managed bean
//@Mapper
public interface BaseMapper<Entity, RequestDTO, ResponseDTO> {
    //RequestDTO toRequest(Entity entity);

    ResponseDTO toResponse(Entity entity);

    Entity toEntity(RequestDTO requestDTO);
}
