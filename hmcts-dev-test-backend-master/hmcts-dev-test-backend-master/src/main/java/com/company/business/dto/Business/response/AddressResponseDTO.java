package com.company.business.dto.Business.response;

import lombok.Getter;
import com.company.business.dto.Business.request.CountryDTO;

@Getter
public class AddressResponseDTO {

    private final String street;
    private final String city;
    private final String county;
    private final String postcode;
    private final  CountryDTO country; // nested, includes name/code

    // Constructor
    public AddressResponseDTO(String street, String city, String county, String postcode, CountryDTO country) {
        this.street = street;
        this.city = city;
        this.county = county;
        this.postcode = postcode;
        this.country = country;
    }
}
