package com.company.business.Mappers;


import com.company.business.dto.Business.request.CountryRequestDTO;
import com.company.business.dto.Business.response.CountryResponseDTO;
import com.company.business.models.country.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CountryMapper extends BaseMapper<Country, CountryRequestDTO, CountryResponseDTO> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cities", ignore = true)
    Country toEntity(CountryRequestDTO dto);

    @Override
    CountryResponseDTO toResponse(Country sector);
}
