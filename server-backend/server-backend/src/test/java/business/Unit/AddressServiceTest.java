package business.Unit;

import com.company.business.Mappers.AddressMapper;
import com.company.business.Services.business.AddressService;
import com.company.business.Services.business.CountryService;
import com.company.business.dto.Business.request.AddressRequestDTO;
import com.company.business.dto.Business.response.AddressResponseDTO;
import com.company.business.exceptions.ResourceNotFoundException;
import com.company.business.models.business.Address;
import com.company.business.models.country.Country;
import com.company.business.repositories.business.AddressRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest{

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private AddressService addressService;

    @Mock
    private CountryService countryService;

    AddressRequestDTO request;

    @BeforeEach
    void Setup(){
        request = new AddressRequestDTO(
            "123 Main Street",
            "New York",
            "Somewhere",
            "12345",
            1L
        );
    }

    @Test
    void shouldReturnAddressWhenIdExists() {

        //mock country
        Country country = new Country();
        country.setId(1L);
        country.setName("UK");

        Address mockAddress = new Address(
            1L,
            "123 Main Street",
            "New York",
            "Somewhere",
            "12345",
            country
        );

        when(addressRepository.findById(1L)).thenReturn(Optional.of(mockAddress));

        AddressResponseDTO result = addressService.getAddressById(1L);

        assertEquals("123 Main Street", result.street());
        verify(addressRepository).findById(1L);
    }
    @Test
    void shouldThrowExceptionWhenAddressNotFound() {
        when(addressRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(ResourceNotFoundException.class, () -> addressService.getAddressById(99L));

        assertEquals("Address not found with id: 99", exception.getMessage());
    }

    @Test
    void shouldCreateAddress() {

        Country country = new Country();
        country.setId(1L);
        country.setName("UK");

        Address address = new Address();
        address.setStreet("123 Main Street");

        AddressResponseDTO response = new AddressResponseDTO(
            1L,
            "123 Main Street",
            "New York",
            "Somewhere",
            "12345",
            1L
        );

        //when(countryService.getById(1L)).thenReturn(country);

        when(addressMapper.toEntity(request)).thenReturn(address);

        when(addressRepository.save(any(Address.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        when(addressMapper.toResponse(any(Address.class))).thenReturn(response);

        AddressResponseDTO result = addressService.createAddress(request);

        assertEquals("123 Main Street", result.street());

        verify(addressMapper).toEntity(request);
        verify(addressRepository).save(any(Address.class));
        verify(addressMapper).toResponse(any(Address.class));
    }

    @Test
    void shouldUpdateAddress() {

        // GIVEN
        Long id = 1L;

        Country country = new Country();
        country.setId(1L);
        country.setName("UK");

        AddressRequestDTO request = new AddressRequestDTO(
            "New Street",
            "New City",
            "New County",
            "99999",
            1L
        );

        Address existing = new Address();
        existing.setId(id);
        existing.setStreet("Old Street");
        existing.setCity("Old City");
        existing.setCounty("Old County");
        existing.setPostcode("00000");
        existing.setCountry(country);

        Address saved = new Address();
        saved.setId(id);
        saved.setStreet("New Street");
        saved.setCity("New City");
        saved.setCounty("New County");
        saved.setPostcode("99999");

        // WHEN
        when(addressRepository.findById(id)).thenReturn(Optional.of(existing)); //When the code calls findById(id), return this fake result instead of going to the database.

        when(addressRepository.save(any(Address.class))).thenReturn(saved); //DB saved the object and returned it

        // ACT
        Address result = addressService.update(id, request);

        // THEN
        assertEquals("New Street", result.getStreet(), "Street updated");
        assertEquals("New City", result.getCity(),  "City updated");
        assertEquals("New County", result.getCounty(),  "County updated");
        assertEquals("99999", result.getPostcode(), "Postcode updated");

        verify(addressRepository).findById(id);
        verify(addressRepository).save(existing);
    }

    @Test
    void testCreate() {
        assertNotNull(request);
    }
    @Test
    void testUpdate() {
        assertEquals("123 Main Street", request.street());
        assertEquals("New York", request.city());
        assertEquals("Somewhere", request.county());
    }

}
