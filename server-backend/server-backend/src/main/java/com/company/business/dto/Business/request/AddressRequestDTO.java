package com.company.business.dto.Business.request;

import com.company.business.models.country.Country;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


public record AddressRequestDTO(
    @NotBlank(message = "Street is required")
    @Size(max = 200)
    String street,

    @NotBlank(message = "City is required")
    @Size(max = 100)
    String city,

    @NotBlank(message = "State is required")
    @Size(max = 50)
    String county,

    @NotBlank(message = "Postcode is required")
    @Size(max = 20)
    String postcode,

    @Schema(example = "1") //tell swagger when showing request example, show ID 1
    @NotNull(message = "Country is required")
    Long countryId
){}
