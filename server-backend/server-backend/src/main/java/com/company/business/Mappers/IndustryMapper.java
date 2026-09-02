package com.company.business.Mappers;

import com.company.business.dto.Business.request.IndustryRequestDTO;
import com.company.business.dto.Business.response.IndustryResponseDTO;
import com.company.business.models.business.Industry;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface IndustryMapper extends BaseMapper<Industry, IndustryRequestDTO, IndustryResponseDTO> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sector", ignore = true)
    Industry toEntity(IndustryRequestDTO dto);


    @Override
    @Mapping(source = "sector", target = "sector")
    IndustryResponseDTO toResponse(Industry industry);
}
