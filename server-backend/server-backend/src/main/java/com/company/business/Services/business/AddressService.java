package com.company.business.Services.business;

import org.springframework.stereotype.Service;

import com.company.business.repositories.business.AddressRepository;

import com.company.business.dto.Business.request.AddressRequestDTO;
import com.company.business.models.business.Address;
import com.company.business.models.country.Country;

@Service
public class AddressService {

    private final CountryService countryService;
    private final AddressRepository addressRepository;

    // Inject CountryService to resolve countryId → Country entity
    public AddressService(CountryService countryService, AddressRepository addressRepository) {
        this.countryService = countryService;
        this.addressRepository = addressRepository;
    }

    // Map AddressDTO from client to Address entity for saving
    public Address mapDtoToAddress(AddressRequestDTO dto) {
        if (dto == null) {
            return null; // handle optional address
        }

        // Fetch Country entity using the ID provided in DTO
        Country country = countryService.getById(dto.getCountryID());

        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setCounty(dto.getCounty());
        address.setPostcode(dto.getPostcode());
        address.setCountry(country);

        return address;
    }

    // Map Address entity to AddressDTO for sending back to client
    public AddressRequestDTO mapAddressToDto(Address address) {
        if (address == null) {
            return null;
        }

        AddressRequestDTO dto = new AddressRequestDTO();
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setCounty(address.getCounty());
        dto.setPostcode(address.getPostcode());
        dto.setCountryID(address.getCountry().getId());

        return dto;
    }

    public Address getAddressById(Long id) {

        return addressRepository.findById(id).orElse(null);
    }

    public Address getAddressByPostcode(String postcode) {
        // Implement logic to retrieve address by postcode
        // This might involve calling a repository method to find the address

        return null; // Placeholder return statement
    }
}
