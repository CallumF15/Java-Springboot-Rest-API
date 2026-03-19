package com.company.business.dto.Business.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CountryResponseDTO {

    private String name;
    private String code;

    // Constructor 1: just name
    public CountryResponseDTO(String name) {
        this.name = name;
    }

    // Constructor 2: Lombok makes
}
