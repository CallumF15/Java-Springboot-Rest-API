package com.company.business.dto.Business.response;

import com.company.business.dto.Business.request.IndustryRequestDTO;
import com.company.business.dto.Business.request.SectorRequestDTO;
import java.time.LocalDateTime;

///note: Only include what the client needs to display, read, or use.

public record BusinessResponseDTO(
    String title,
     String description,

     SectorRequestDTO sector,
     IndustryRequestDTO industry,

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
