package com.company.business.dto.Business.request;

//request DTO

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

//Receive data from the client

public record BusinessRequestDTO(
    @NotBlank
    @Size(max = 200)
     String title,

    @Size(max = 2000)
    String description,

    @NotNull
    Long sectorId,

    @NotNull
    Long industryId,   // client sends only the ID (can derive sector ID from this)

    // Contact Info
    @Email
    @Size(max = 200)
    String email,

    @Size(max = 20)
    String landlineNumber,

    @Size(max = 20)
    String phoneNumber,

    AddressRequestDTO address, // DTO for address

    @URL
    @Size(max = 500)
    String website,

    // Optional / Flags
    @Size(max = 500)
    String logoUrl
){}
