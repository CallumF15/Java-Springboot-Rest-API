package com.company.business.controllers.business;

import com.company.business.Services.business.AddressService;
import com.company.business.Services.business.CountryService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequestMapping("/api/address") //will change this later
public class AddressController {

    private final AddressService addressService;
    private final CountryService countryService;

    public AddressController(AddressService addressService, CountryService countryService) {
        this.addressService = addressService;
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public List<String> getAllCountries() {
        return countryService.getAllCountryNamesAsStrings();
    }
}
