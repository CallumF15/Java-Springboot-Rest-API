package com.company.business.dto.Business.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import com.company.business.dto.Business.request.IndustryDTO;
import com.company.business.dto.Business.request.SectorDTO;
import java.time.LocalDateTime;

///note: Only include what the client needs to display, read, or use.

@Getter
@Setter
@AllArgsConstructor
public class BusinessResponseDTO {
    private String title;
    private String description;

    private SectorDTO sector;
    private IndustryDTO industry;

    private String email;
    private String landlineNumber;
    private String phoneNumber;

    private AddressResponseDTO address;

    private String website;
    private String logoUrl;
    private Boolean isActive;

    private LocalDateTime createdAt;  // safe to show
    private LocalDateTime updatedAt;  // safe to show
}
