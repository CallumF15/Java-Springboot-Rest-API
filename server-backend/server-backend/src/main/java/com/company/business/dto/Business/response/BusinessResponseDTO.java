package com.company.business.dto.Business.response;

import com.company.business.dto.Business.summary.IndustrySummaryDTO;
import com.company.business.dto.Business.summary.SectorSummaryDTO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;

///note: Only include what the client needs to display, read, or use.

@JsonPropertyOrder({
    "title",
    "description",
    "sector",
    "industry",
    "email",
    "landlineNumber",
    "phoneNumber",
    "address",
    "website",
    "logoUrl",
    "isActive",
    "createdAt",
    "updatedAt"
})
public record BusinessResponseDTO(
     String title,
     String description,

     SectorSummaryDTO sector,
     IndustrySummaryDTO industry,

     String email,
     String landlineNumber,
     String phoneNumber,

     AddressResponseDTO address,

     String website,
     String logoUrl,
     Boolean isActive,

     LocalDateTime createdAt,  // safe to show
     LocalDateTime updatedAt  // safe to show
){}
