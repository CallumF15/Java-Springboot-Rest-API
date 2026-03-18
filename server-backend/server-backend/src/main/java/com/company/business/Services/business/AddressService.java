package com.company.business.Services.business;

import org.springframework.stereotype.Service;
import com.company.business.dto.Business.request.AddressDTO;
import com.company.business.models.business.Address;
import com.company.business.models.country.Country;

@Service
public class AddressService {

    private final CountryService countryService;

    // Inject CountryService to resolve countryId → Country entity
    public AddressService(CountryService countryService) {
        this.countryService = countryService;
    }

    // Map AddressDTO from client to Address entity for saving
    public Address mapDtoToAddress(AddressDTO dto) {
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
    public AddressDTO mapAddressToDto(Address address) {
        if (address == null) {
            return null;
        }

        AddressDTO dto = new AddressDTO();
        dto.setStreet(address.getStreet());
        dto.setCity(address.getCity());
        dto.setCounty(address.getCounty());
        dto.setPostcode(address.getPostcode());
        dto.setCountryID(address.getCountry().getId());

        return dto;
    }
}
