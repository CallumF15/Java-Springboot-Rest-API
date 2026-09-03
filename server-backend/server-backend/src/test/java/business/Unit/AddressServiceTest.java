package business.Unit;

import com.company.business.Mappers.AddressMapper;
import com.company.business.Services.business.AddressService;
import com.company.business.Services.business.CountryService;
import com.company.business.dto.Business.request.AddressRequestDTO;
import com.company.business.dto.Business.response.AddressResponseDTO;
import com.company.business.dto.Business.response.CountryResponseDTO;
import com.company.business.exceptions.ResourceNotFoundException;
import com.company.business.models.business.Address;
import com.company.business.models.country.Country;
import com.company.business.repositories.Location.CountryRepository;
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
import static org.mockito.Mockito.*;


import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest{

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private AddressMapper addressMapper;

    @InjectMocks
    private AddressService addressService;

    private AddressRequestDTO request;
    private AddressResponseDTO response;

    @BeforeEach
    void Setup()
    {
        request = new AddressRequestDTO(
            "123 Main Street",
            "New York",
            "Somewhere",
            "12345",
            1L
        );

        response = new AddressResponseDTO(
            1L,
            "123 Main Street",
            "New York",
            "Somewhere",
            "12345",
            new CountryResponseDTO(
                1L,
                "United Kingdom",
                "GB"
            )
        );
    }

    @Test
    void shouldReturnAddressWhenIdExists() {

        //mock country
        Country country = new Country();
        country.setId(1L);
        country.setName("United Kingdom");
        country.setCode("GB");

        Address mockAddress = new Address(
            1L,
            "123 Main Street",
            "New York",
            "Somewhere",
            "12345",
            country
        );

        when(addressRepository.findById(1L)).thenReturn(Optional.of(mockAddress));

        when(addressMapper.toResponse(mockAddress)).thenReturn(response);

        AddressResponseDTO result = addressService.getAddressById(1L);

        assertEquals("123 Main Street", result.street());
        assertEquals("United Kingdom", result.country().name());

        verify(addressRepository).findById(1L);
        verify(addressMapper).toResponse(mockAddress);
    }
    @Test
    void shouldThrowExceptionWhenAddressNotFound()
    {
        when(addressRepository.findById(99L)).thenReturn(Optional.empty());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,() -> addressService.getAddressById(99L));

        assertEquals("Address not found with id: 99", exception.getMessage());

        verify(addressRepository).findById(99L);
        verifyNoInteractions(addressMapper);
    }

    @Test
    void shouldCreateAddress()
    {
        Country country = new Country();
        country.setId(1L);
        country.setName("United Kingdom");
        country.setCode("GB");

        Address address = new Address();
        address.setStreet("123 Main Street");
        address.setCity("New York");
        address.setCounty("Somewhere");
        address.setPostcode("12345");
        address.setCountry(country);


        when(countryRepository.findById(1L)).thenReturn(Optional.of(country));
        when(addressMapper.toEntity(request)).thenReturn(address);
        when(addressRepository.save(any(Address.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(addressMapper.toResponse(any(Address.class))).thenReturn(response);

        AddressResponseDTO result = addressService.createAddress(request);

        assertEquals("123 Main Street", result.street());
        assertEquals("United Kingdom", result.country().name());

        verify(addressMapper).toEntity(request);
        verify(addressRepository).save(address);
        verify(addressMapper).toResponse(address);

        //////////////////////////
//        verify(addressMapper).toEntity(request);
//        verify(addressRepository).save(any(Address.class));
//        verify(addressMapper).toResponse(any(Address.class));
    }

    @Test
    void shouldUpdateAddress()
    {
        Long id = 1L;

        AddressRequestDTO updateRequest =
            new AddressRequestDTO(
                "New Street",
                "New City",
                "New County",
                "99999",
                1L
            );

        Country country = new Country();
        country.setId(1L);
        country.setName("United Kingdom");
        country.setCode("GB");

        Address existing = new Address();
        existing.setId(id);
        existing.setStreet("Old Street");
        existing.setCity("Old City");
        existing.setCounty("Old County");
        existing.setPostcode("00000");
        existing.setCountry(country);

        AddressResponseDTO response =
            new AddressResponseDTO(
                id,
                "New Street",
                "New City",
                "New County",
                "99999",
                new CountryResponseDTO(
                    1L,
                    "United Kingdom",
                    "GB"
                )
            );

        when(addressRepository.findById(id)).thenReturn(Optional.of(existing));
        when(countryRepository.findById(1L)).thenReturn(Optional.of(country));
        when(addressRepository.save(existing)).thenReturn(existing);
        when(addressMapper.toResponse(existing)).thenReturn(response);

        AddressResponseDTO result = addressService.update(id, updateRequest);

        assertEquals("New Street", result.street());
        assertEquals("New City", result.city());
        assertEquals("New County", result.county());
        assertEquals("99999", result.postcode());
        assertEquals("United Kingdom", result.country().name());
        assertEquals("GB", result.country().code());

        verify(addressRepository).findById(id);
        verify(countryRepository).findById(1L);
        verify(addressRepository).save(existing);
        verify(addressMapper).toResponse(existing);
    }
}
