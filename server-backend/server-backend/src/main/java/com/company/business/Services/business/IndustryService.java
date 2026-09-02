package com.company.business.Services.business;

import com.company.business.dto.Business.summary.SectorSummaryDTO;
import org.springframework.stereotype.Service;
import com.company.business.dto.Business.response.IndustryResponseDTO;
import com.company.business.models.business.Industry;

@Service
public class IndustryService {

    public IndustryResponseDTO mapToResponseDTO(Industry industry) {
        return new IndustryResponseDTO(
            industry.getId(),
            industry.getName(),
            new SectorSummaryDTO(
                industry.getSector().getId(),
                industry.getSector().getName()
            )
        );
    }
}
