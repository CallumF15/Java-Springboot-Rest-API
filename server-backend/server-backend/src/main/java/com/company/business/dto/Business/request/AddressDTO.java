package com.company.business.dto.Business.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDTO {

    @NotBlank(message = "Street is required")
    @Size(max = 200)
    private String street;

    @NotBlank(message = "City is required")
    @Size(max = 100)
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 50)
    private String county;

    @NotBlank(message = "Postcode is required")
    @Size(max = 20)
    private String postcode;

    @NotNull(message = "Country is required")
    private Long countryID;
}
