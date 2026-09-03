package business.Unit;


import com.company.business.Mappers.AddressMapper;
import com.company.business.Mappers.BusinessMapper;
import com.company.business.Services.business.AddressService;
import com.company.business.Services.business.BusinessService;
import com.company.business.dto.Business.request.AddressRequestDTO;
import com.company.business.dto.Business.request.BusinessRequestDTO;
import com.company.business.dto.Business.response.AddressResponseDTO;
import com.company.business.dto.Business.response.BusinessResponseDTO;
import com.company.business.dto.Business.response.CountryResponseDTO;
import com.company.business.dto.Business.summary.IndustrySummaryDTO;
import com.company.business.dto.Business.summary.SectorSummaryDTO;
import com.company.business.models.business.Business;
import com.company.business.repositories.Location.CountryRepository;
import com.company.business.repositories.business.AddressRepository;
import com.company.business.repositories.business.BusinessRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
public class BusinessServiceTest
{

    @Mock
    private BusinessRepository businessRepository;


    @Mock
    private BusinessMapper businessMapper;

    @InjectMocks
    private BusinessService businessService;

    private BusinessRequestDTO request;
    private BusinessResponseDTO response;

    @BeforeEach
    void Setup()
    {
        request = new BusinessRequestDTO(
            "Northstar Technologies Ltd",
            "A technology company specialising in software development and cloud solutions.",
            1L, // sectorId
            2L, // industryId
            "contact@northstartech.co.uk",
            "01415550123",
            "07700900123",
            new AddressRequestDTO(
                "45 Buchanan Street",
                "Glasgow",
                "Glasgow City",
                "G2 3AB",
                1L // countryId
            ),
            "https://northstartech.co.uk",
            "https://northstartech.co.uk/images/logo.png"
        );

         response = new BusinessResponseDTO(
             "Northstar Technologies Ltd",
             "A technology company specialising in software development and cloud solutions.",

             new SectorSummaryDTO(
                1L,
                "Technology"
            ),

             new IndustrySummaryDTO(
                2L,
                "Software Development"
            ),

             "contact@northstartech.co.uk",
             "01415550123",
             "07700900123",

             new AddressResponseDTO(
                1L,
                "45 Buchanan Street",
                "Glasgow",
                "Glasgow City",
                "G2 3AB",
                new CountryResponseDTO(
                    1L,
                    "United Kingdom",
                    "GB"
                )
            ),

             "https://northstartech.co.uk",
             "https://northstartech.co.uk/images/logo.png",
             true,

             LocalDateTime.of(2026, 9, 3, 10, 0),
             LocalDateTime.of(2026, 9, 3, 15, 30)
        );
    }


    /**
     * BASIC CRUD  TESTING
     */
    @Test
    void shouldCreateBusiness(){

    }

    @Test
    void shouldUpdateBusiness()
    {

    }

    @Test
    void shouldDeleteBusiness()
    {

    }

    @Test
    void shouldThrowExceptionWhenDeletingMissingBusiness()
    {

    }

    @Test
    void shouldThrowExceptionWhenBusinessNotFound(){

    }

    /**
     * NOT FOUND DURING CREATE TESTING
     */
    @Test
    void shouldThrowExceptionWhenIndustryNotFoundDuringCreate()
    {

    }


    /**
     * NOT FOUND DURING UPDATE TESTING
     */
    @Test
    void shouldThrowExceptionWhenBusinessNotFoundDuringUpdate()
    {

    }

    @Test
    void shouldThrowExceptionWhenIndustryNotFoundDuringUpdate(){

    }

    @Test
    void shouldThrowExceptionWhenCountryNotFoundDuringUpdate(){

    }
    
    @Test
    void shouldReturnBusinessWhenIDExists()
    {
        Business business = new Business();
    }

    @Test
    void shouldReturnBusinessesWhenIndustryExists()
    {
        Business business = new Business();
    }

    @Test
    void shouldThrowExceptionWhenIndustryDoesNotBelongToSector()
    {

    }

    @Test
    void shouldThrowExceptionWhenCountryNotFoundDuringCreate()
    {

    }
}
