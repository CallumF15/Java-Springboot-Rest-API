package com.company.business.dto.Business.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IndustryDTO {
    private String name;
    private Long sectorId;  // client selects which sector this industry belongs
}
