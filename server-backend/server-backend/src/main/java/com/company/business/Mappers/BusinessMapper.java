package com.company.business.Mappers;

import com.company.business.dto.Business.request.BusinessRequestDTO;
import com.company.business.dto.Business.response.BusinessResponseDTO;
import com.company.business.models.business.Business;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    uses = {
        AddressMapper.class,
        IndustryMapper.class,
        SectorMapper.class
    }
)
public interface BusinessMapper extends BaseMapper<Business, BusinessRequestDTO, BusinessResponseDTO>
{
    @Override
    @Mapping(target = "id", ignore = true)  //Mapping -> Do not try to map these properties from the DTO. Leave them alone and let something else handle their values.
    @Mapping(target = "industry", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Business toEntity(BusinessRequestDTO dto); //MapStruct will generate code

    @Mapping(
        target = "sector",
        source = "industry.sector"
    )
    BusinessResponseDTO toResponse(Business business);
}
