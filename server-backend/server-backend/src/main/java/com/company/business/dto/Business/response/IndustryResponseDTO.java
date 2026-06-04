package com.company.business.dto.Business.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

public record IndustryResponseDTO(
    Long id,
    String name,
    Long sectorId,
    String sectorName

){}
