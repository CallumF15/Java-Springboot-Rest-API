package com.company.business.dto.Business.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IndustryResponseDTO {
    private final Long id;
    private final String name;
    private final Long sectorId;   // optional: client might want to know which sector it belongs to
    private final String sectorName; // optional for easier display
}
