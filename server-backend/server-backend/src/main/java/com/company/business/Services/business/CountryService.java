package com.company.business.Services.business;

import com.company.business.dto.Business.response.CountryResponseDTO;
import com.company.business.exceptions.CountryNotFoundException;
import org.springframework.stereotype.Service;
import com.company.business.models.country.Country;
import com.company.business.repositories.business.CountryRepository;
import com.company.business.exceptions.CountryNotFoundException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

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

    // Fetch a Country entity by ID, throw exception if not found
    public CountryResponseDTO getCountryById(Long id) {
        return countryRepository.findById(id)
            .map(c -> new CountryResponseDTO(c.getId(), c.getName(), c.getCode()))
            .orElseThrow(() -> new CountryNotFoundException(id));
    }

    public List<CountryResponseDTO> getAllCountries() {
        return countryRepository.findAll()
            .stream()
            .map(c -> new CountryResponseDTO(c.getId(), c.getName(), c.getCode()))
            .collect(Collectors.toList());
    }

    public List<String> getAllCountryNames() {
        return countryRepository.findAll()
            .stream()
            .map(Country::getName)
            .collect(Collectors.toList());
    }

    public List<String> getAllCountryCodes() {
        return countryRepository.findAll()
            .stream()
            .map(Country::getCode)
            .collect(Collectors.toList());
    }

    // Optional: validate if country exists for some business rule
    public boolean existsById(Long id) {
        return countryRepository.existsById(id);
    }
}
