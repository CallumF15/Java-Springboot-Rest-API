package business.Unit;

import com.company.business.Mappers.BaseMapper;
import com.company.business.Services.business.CountryService;
import com.company.business.dto.Business.request.AddressRequestDTO;
import com.company.business.dto.Business.request.CountryRequestDTO;
import com.company.business.dto.Business.response.AddressResponseDTO;
import com.company.business.dto.Business.response.CountryResponseDTO;
import com.company.business.exceptions.ResourceNotFoundException;
import com.company.business.models.business.Address;
import com.company.business.models.country.Country;
import com.company.business.repositories.Location.CountryRepository;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CountryServiceTest {

    @Mock
    private CountryRepository countryRepository;
    @InjectMocks
    private CountryService countryService;

    @Mock
    private BaseMapper<Country, CountryRequestDTO, CountryResponseDTO> mapper;

    CountryRequestDTO request;

    @BeforeEach
    void Setup(){
        request = new CountryRequestDTO(
            "New Zealand",
                  "NZ"
        );
    }

    @Test
    void shouldCreateCountry() {

        //Country result = countryService.createCountry(request);

        //assertEquals("New Zealand", result.getName());
        //assertEquals("NZ", result.getCode());
    }
    @Test
    void shouldReturnCountryWhenNameExists() {

        String countryName = request.name();
        String countryCode = request.code();

        //mock country
        Country mockCountry = new Country();
        mockCountry.setId(1L);
        mockCountry.setName(countryName);
        mockCountry.setCode(countryCode);

        ///👉 “When the method findByName(countryName) is called…”
        /// CountryName must match EXACTLY what the service will pass
        /// Mockito uses strict matching unless you use matchers like anyString()
        /// Optional -> is a container object used to represent a value that may or may not be present.
        when(countryRepository.findByName(countryName)).thenReturn(Optional.of(mockCountry));

        CountryResponseDTO result = countryService.getCountryByName(countryName);

        assertEquals(countryName, result.name());
        assertEquals(countryCode, result.code());
        verify(countryRepository).findByName(countryName);
    }
    @Test
    void shouldThrowExceptionWhenCountryNotFound() {
        when(countryRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(ResourceNotFoundException.class, () -> countryService.getCountryById(99L));

        assertEquals("country not found with id: 99", exception.getMessage());
    }
}
