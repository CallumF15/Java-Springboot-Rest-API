package com.company.business.dto.Business.request;

import com.company.business.models.country.Country;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
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
    @Size(max = 8, message = "Postcode must not exceed 8 characters")
    @Pattern(
        regexp = "^[A-Za-z]{1,2}\\d[A-Za-z\\d]?\\s?\\d[A-Za-z]{2}$",     //Pattern requires testing | also, need to add other countries too
        message = "Postcode must be a valid UK postcode"
    )
    String postcode,

    @Schema(example = "1") //tell swagger when showing request example, show ID 1
    @NotNull(message = "Country is required")
    @Positive(message = "Country ID must be greater than 0")
    Long countryId
){}
