package com.company.business.dto.Business.response;

import com.company.business.dto.Business.request.CountryRequestDTO;

public record AddressResponseDTO(
    Long id,
    String street,
    String city,
    String county,
    String postcode,
    CountryRequestDTO country // nested, includes name/code
) { }
