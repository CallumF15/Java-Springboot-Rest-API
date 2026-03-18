package com.company.business.Services.business;

import com.company.business.dto.Business.response.CountryResponseDTO;
import org.springframework.stereotype.Service;
import com.company.business.models.country.Country;
import com.company.business.repositories.business.CountryRepository;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<CountryResponseDTO> getAllCountries() {
        return countryRepository.findAll()
            .stream()
            .map(c -> new CountryResponseDTO(c.getName())) // <-- closing parenthesis here
            .collect(Collectors.toList());
    }

    // Optional: validate if country exists for some business rule
    public boolean existsById(Long id) {
        return countryRepository.existsById(id);
    }
}
