package com.company.business.Services.business;

import com.company.business.Mappers.AddressMapper;
import com.company.business.dto.Business.response.AddressResponseDTO;
import com.company.business.dto.Business.response.CountryResponseDTO;
import com.company.business.exceptions.ResourceNotFoundException;
import com.company.business.models.country.Country;
import org.springframework.stereotype.Service;

import com.company.business.repositories.business.AddressRepository;

import com.company.business.dto.Business.request.AddressRequestDTO;
import com.company.business.models.business.Address;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    private final AddressMapper addressMapper;

    // Inject CountryService to resolve countryId → Country entity
    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public AddressResponseDTO createAddress(AddressRequestDTO dto) {
        Address address = addressMapper.toEntity(dto);
        Address saved = addressRepository.save(address);
        return addressMapper.toResponse(saved);
    }

    public Address update(Long id, AddressRequestDTO request) {

        Address existing = addressRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id, "Address "));

        existing.setStreet(request.street());
        existing.setCity(request.city());
        existing.setCounty(request.county());
        existing.setPostcode(request.postcode());

        return addressRepository.save(existing);
    }


    // Map Address entity to AddressDTO for sending back to client
    public AddressResponseDTO ToDto(Address address) {
        return addressMapper.toResponse(address);
    }

    public AddressResponseDTO getAddressById(Long id)
    {
        return addressRepository.findById(id)
            .map(addressMapper::toResponse)
            .orElseThrow(() -> new ResourceNotFoundException(id, "Address"));
    }

    public List<AddressResponseDTO> getAllAddresses()
    {
        return addressRepository.findAll()
            .stream() //turns the list into a stream so we can process each item
            .map(addressMapper::toResponse) //converts EACH Address entity into an AddressResponseDTO
            .collect(Collectors.toList()); // turns it back into a List<AddressResponseDTO>
    }


    public Address getAddressByPostcode(String postcode)
    {
        // Implement logic to retrieve address by postcode


        return null; // Placeholder return statement
    }
}
