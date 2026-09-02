package com.company.business.dto.Business.response;

import com.company.business.dto.Business.summary.SectorSummaryDTO;

public record IndustryResponseDTO(
    Long id,
    String name,
    SectorSummaryDTO sector
){}
