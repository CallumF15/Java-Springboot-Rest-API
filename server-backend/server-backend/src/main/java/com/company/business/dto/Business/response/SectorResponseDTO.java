package com.company.business.dto.Business.response;
import com.company.business.dto.Business.summary.IndustrySummaryDTO;

import java.util.List;

public record SectorResponseDTO(
    Long id,
    String name,
    List<IndustrySummaryDTO> industries
){}
