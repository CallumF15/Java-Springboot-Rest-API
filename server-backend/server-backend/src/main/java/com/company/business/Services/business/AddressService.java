package com.company.business.Services.business;

import com.company.business.Mappers.AddressMapper;
import com.company.business.dto.Business.response.AddressResponseDTO;
import com.company.business.dto.Business.response.CountryResponseDTO;
import com.company.business.exceptions.ResourceNotFoundException;
import com.company.business.models.country.Country;
import com.company.business.repositories.Location.CountryRepository;
import org.springframework.stereotype.Service;

import com.company.business.repositories.business.AddressRepository;

import com.company.business.dto.Business.request.AddressRequestDTO;
import com.company.business.models.business.Address;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CountryRepository countryRepository;

    private final AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, CountryRepository countryRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.countryRepository = countryRepository;
        this.addressMapper = addressMapper;
    }
    
    /**
     * Creates a new address associated with the specified country.
     *
     * @param dto the address details, including the country ID
     * @return the created address
     * @throws ResourceNotFoundException if the specified country does not exist
     */
    public AddressResponseDTO createAddress(AddressRequestDTO dto)
    {
        Country country = countryRepository.findById(dto.countryId())
            .orElseThrow(() -> new ResourceNotFoundException(dto.countryId(), "Country"));

        Address address = addressMapper.toEntity(dto);
        address.setCountry(country);

        Address saved = addressRepository.save(address);
        return addressMapper.toResponse(saved);
    }

    /**
     * Updates an existing address with the supplied address details.
     *
     * @param id the ID of the address to update
     * @param request the new address details, including the country ID
     * @return the updated address
     * @throws ResourceNotFoundException if the address or specified country does not exist
     */
    public AddressResponseDTO update(Long id, AddressRequestDTO request) {

        Address existing = addressRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id, "Address "));

        Country country = countryRepository.findById(request.countryId())
            .orElseThrow(() -> new ResourceNotFoundException(request.countryId(), "Country")); //find requested country in DB

        existing.setStreet(request.street());
        existing.setCity(request.city());
        existing.setCounty(request.county());
        existing.setPostcode(request.postcode());
        existing.setCountry(country);

        Address saved = addressRepository.save(existing);

        return addressMapper.toResponse(saved);
    }

    /**
     * Deletes an address with the specified ID.
     *
     * @param id the ID of the address to delete
     * @throws ResourceNotFoundException if an address with the specified ID does not exist
     */
    public void deleteAddress(Long id)
    {
        Address address = addressRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id, "Address"));

        addressRepository.delete(address);
    }

    /**
     * Retrieves an address by its unique ID.
     *
     * @param id the ID of the address to retrieve
     * @return the address matching the specified ID
     * @throws ResourceNotFoundException if an address with the specified ID does not exist
     */
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


    /**
     * Retrieves all addresses whose postcodes start with the specified value,
     * ignoring differences in letter case.
     * -
     * Postcodes are not unique to a single address, as multiple addresses can share the same postcode.
     * -
     * @param postcode the full or partial postcode used to search for addresses
     * @return a list of addresses with matching postcodes
     */
    public List<AddressResponseDTO> getAddressesByPostcode(String postcode)
    {
        return addressRepository
            .findByPostcodeStartingWithIgnoreCase(postcode)
            .stream()
            .map(addressMapper::toResponse)
            .toList();
    }

    /**
     * Retrieves all addresses matching the specified city,
     * ignoring differences in letter case.
     *
     * @param city the city used to find addresses
     * @return a list of addresses matching the specified city
     */
    public List<AddressResponseDTO> getAddressesByCity(String city)
    {
        return addressRepository
            .findByCityIgnoreCase(city)
            .stream()
            .map(addressMapper::toResponse)
            .toList();
    }
}
