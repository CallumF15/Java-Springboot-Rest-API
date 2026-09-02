package com.company.business.Mappers;

import com.company.business.dto.Business.request.BusinessRequestDTO;
import com.company.business.dto.Business.request.SectorRequestDTO;
import com.company.business.dto.Business.response.SectorResponseDTO;
import com.company.business.models.business.Business;
import com.company.business.models.business.Sector;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    componentModel = "spring",
    uses = {
        IndustryMapper.class
    }
)
public interface SectorMapper extends BaseMapper<Sector, SectorRequestDTO, SectorResponseDTO> {

    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "industries", ignore = true)
    Sector toEntity(SectorRequestDTO dto);

    @Override
    SectorResponseDTO toResponse(Sector sector);
}
