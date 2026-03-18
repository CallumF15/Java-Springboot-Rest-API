package com.company.business.Services.business;

import org.springframework.stereotype.Service;
import com.company.business.models.country.Country;
import com.company.business.repositories.business.CountryRepository;

import java.util.List;

@Service
public class CountryService {


    private final CountryRepository countryRepository;

    // Constructor injection
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    // Fetch a Country entity by ID, throw exception if not found
    public Country getById(Long id) {
        return countryRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Country not found with ID: " + id));
    }

    //Get all countries
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    // Optional: validate if country exists for some business rule
    public boolean existsById(Long id) {
        return countryRepository.existsById(id);
    }
}
