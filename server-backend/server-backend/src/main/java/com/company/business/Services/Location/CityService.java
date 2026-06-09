package com.company.business.Services.Location;

import com.company.business.dto.Business.response.CityResponseDTO;
import com.company.business.exceptions.ResourceNotFoundException;
import com.company.business.repositories.Location.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public CityResponseDTO getCityById(Long id) {
        return cityRepository.findById(id)
            .map(c -> new CityResponseDTO(c.getId(), c.getName(), c.getCountry().getId()))
            .orElseThrow(() -> new ResourceNotFoundException(id, "address"));
    }

    public List<CityResponseDTO> getAllCities() {
        return cityRepository.findAll()
            .stream()
            .map(c -> new CityResponseDTO(c.getId(), c.getName(), c.getCountry().getId()))
            .collect(Collectors.toList());
    }
}
