package uk.gov.hmcts.reform.dev.models.business;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.hmcts.reform.dev.models.country.Country;

@Embeddable
@NoArgsConstructor //gens constructor no args
@AllArgsConstructor //gens contructor with 1 arg for every field in class
@Getter
@Setter
public class Address {

    @NotBlank(message = "Street is required")
    @Size(max = 200)
    private String street;

    @NotBlank(message = "City is required")
    @Size(max = 100)
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 50)
    private String county;

    @NotBlank(message = "Postcode is required")
    @Size(max = 20)
    private String postcode;

    @ManyToOne
    private Country country;
}
