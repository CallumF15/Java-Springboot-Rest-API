package com.company.business.controllers.business;


import com.company.business.Services.business.CountryService;
import com.company.business.dto.Business.response.CountryResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public List<CountryResponseDTO> getCountries() {
        return countryService.getAllCountries();
    }
}
