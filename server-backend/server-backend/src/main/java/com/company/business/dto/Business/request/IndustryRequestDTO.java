package com.company.business.dto.Business.request;

public record IndustryRequestDTO(
    String name,
    Long sectorId  // client selects which sector this industry belongs
){}
