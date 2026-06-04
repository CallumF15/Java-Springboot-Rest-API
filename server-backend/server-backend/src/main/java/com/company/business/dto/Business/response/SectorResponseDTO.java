package com.company.business.dto.Business.response;
import java.util.List;

public record SectorResponseDTO(
    Long id,
    String name,
    List<IndustryResponseDTO> industries
){}
