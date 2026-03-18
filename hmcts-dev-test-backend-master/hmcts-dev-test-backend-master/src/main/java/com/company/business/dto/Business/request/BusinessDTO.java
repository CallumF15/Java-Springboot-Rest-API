package com.company.business.dto.Business.request;

//request DTO

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

//Receive data from the client

@Getter
@Setter
public class BusinessDTO {

    @NotBlank
    @Size(max = 200)
    private String title;

    @Size(max = 2000)
    private String description;

    @NotNull
    private Long sectorId;     // client sends only the ID

    @NotNull
    private Long industryId;   // client sends only the ID

    // Contact Info
    @Email
    @Size(max = 200)
    private String email;

    @Size(max = 20)
    private String landlineNumber;

    @Size(max = 20)
    private String phoneNumber;

    private AddressDTO address; // DTO for address

    @URL
    @Size(max = 500)
    private String website;

    // Optional / Flags
    @Size(max = 500)
    private String logoUrl;
}
