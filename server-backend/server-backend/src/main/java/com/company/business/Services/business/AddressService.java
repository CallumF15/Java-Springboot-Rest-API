package com.company.business.Services.business;

import com.company.business.Mappers.AddressMapper;
import com.company.business.dto.Business.response.AddressResponseDTO;
import com.company.business.dto.Business.response.CountryResponseDTO;
import com.company.business.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.company.business.repositories.business.AddressRepository;

import com.company.business.dto.Business.request.AddressRequestDTO;
import com.company.business.models.business.Address;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {


    private final CountryService countryService;
    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper;

    // Inject CountryService to resolve countryId → Country entity
    public AddressService(CountryService countryService, AddressRepository addressRepository, AddressMapper addressMapper) {
        this.countryService = countryService;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public AddressResponseDTO createAddress(AddressRequestDTO dto) {
        Address address = addressMapper.toEntity(dto);
        Address saved = addressRepository.save(address);
        return addressMapper.toResponse(saved);
    }


    // Map Address entity to AddressDTO for sending back to client
    public AddressResponseDTO ToDto(Address address) {
        return addressMapper.toResponse(address);
    }

    public AddressResponseDTO getAddressById(Long id) {
        return addressRepository.findById(id)
            .map(c -> new AddressResponseDTO(c.getId(), c.getStreet(), c.getCity(), c.getCounty(), c.getPostcode(), c.getCountry().getId()))
            .orElseThrow(() -> new ResourceNotFoundException(id, "address"));
    }


    public List<AddressResponseDTO> getAllAddresses() {
        return addressRepository.findAll()
            .stream()
            .map(c -> new AddressResponseDTO(c.getId(), c.getStreet(), c.getCity(), c.getCounty(), c.getPostcode(), c.getCountry().getId()))
            .collect(Collectors.toList());
    }

    public Address getAddressByPostcode(String postcode) {
        // Implement logic to retrieve address by postcode
        // This might involve calling a repository method to find the address

        return null; // Placeholder return statement
    }
}
