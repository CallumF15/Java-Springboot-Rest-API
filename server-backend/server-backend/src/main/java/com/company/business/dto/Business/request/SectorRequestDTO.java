package com.company.business.dto.Business.request;

import jakarta.validation.constraints.NotBlank;

public record SectorRequestDTO(
    @NotBlank(message = "Sector name is required")
    String name
){}
