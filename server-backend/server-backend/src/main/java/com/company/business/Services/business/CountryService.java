package com.company.business.Services.business;


import com.company.business.Mappers.CountryMapper;
import com.company.business.dto.Business.request.CountryRequestDTO;
import com.company.business.dto.Business.response.CountryResponseDTO;
import com.company.business.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import com.company.business.models.country.Country;
import com.company.business.repositories.Location.CountryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    // Constructor injection
    public CountryService(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryMapper = countryMapper;
        this.countryRepository = countryRepository;
    }


    public CountryResponseDTO createCountry(CountryRequestDTO dto) {
        Country country = countryMapper.toEntity(dto);
        Country saved = countryRepository.save(country);
        return countryMapper.toResponse(saved);
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
            .orElseThrow(() -> new ResourceNotFoundException(id, "country"));
    }

    public CountryResponseDTO getCountryByName(String name) {
        return countryRepository.findByName(name)
            .map(c -> new CountryResponseDTO(c.getId(), c.getName(), c.getCode()))
            .orElseThrow(() -> new ResourceNotFoundException(name, "country"));
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
