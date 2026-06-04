package com.company.business.dto.Business.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryRequestDTO {
    private String name;
    private String code;   // optional ISO code
}
