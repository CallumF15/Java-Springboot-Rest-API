package com.company.business.dto.Business.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SectorResponseDTO {

    private final Long id;
    private final String name;
    private final List<IndustryResponseDTO> industries; // nested DTOs
}
