package com.company.business.dto.Business.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDTO {
    private Long id;       // country ID
    private String name;

    //private String code;   // optional ISO code
}
