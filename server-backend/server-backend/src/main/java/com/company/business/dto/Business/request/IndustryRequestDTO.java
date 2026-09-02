package com.company.business.dto.Business.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record IndustryRequestDTO(
    @NotBlank(message = "Industry name is required")
    String name,

    @Schema(example = "1")
    @NotNull(message = "Sector ID is required")
    @Positive(message = "Sector ID must be greater than 0")
    Long sectorId
){}
