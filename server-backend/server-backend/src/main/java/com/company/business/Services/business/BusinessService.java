package com.company.business.Services.business;

import java.util.List;

import com.company.business.Mappers.AddressMapper;
import com.company.business.Mappers.BusinessMapper;
import com.company.business.dto.Business.response.BusinessResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//dto
import com.company.business.dto.Business.request.BusinessRequestDTO;


//models
import com.company.business.models.business.Address;
import com.company.business.models.business.Business;
import com.company.business.models.business.Industry;
import com.company.business.models.business.Sector;
import com.company.business.models.country.Country;
import com.company.business.repositories.business.BusinessRepository;
import com.company.business.repositories.Location.CountryRepository;
import com.company.business.repositories.business.IndustryRepository;
import com.company.business.repositories.business.SectorRepository;

import jakarta.persistence.EntityNotFoundException;

//Service layer: contains the logic to get data, combine it, validate, etc.

@Service
public class BusinessService {

    private final SectorRepository sectorRepository;
    private final IndustryRepository industryRepository;
    private final BusinessRepository businessRepository;
    private final CountryRepository countryRepository;

    private final AddressMapper addressMapper;
    private final BusinessMapper businessMapper;

    @Autowired
    public BusinessService(BusinessRepository businessRepository, SectorRepository sectorRepository, IndustryRepository industryRepository, CountryRepository countryRepository,
                           AddressMapper addressMapper, BusinessMapper BusinessMapper)
    {
        //repositories
        this.businessRepository = businessRepository;
        this.sectorRepository = sectorRepository;
        this.industryRepository = industryRepository;
        this.countryRepository = countryRepository;

        //mappers
        this.addressMapper = addressMapper;
        this.businessMapper = BusinessMapper;
    }

    public BusinessResponseDTO createBusiness(BusinessRequestDTO dto)
    {
        Sector sector = sectorRepository.findById(dto.sectorId()).orElseThrow(() -> new RuntimeException("Sector not found"));
        Industry industry = industryRepository.findById(dto.industryId()).orElseThrow(() -> new RuntimeException("Industry not found"));
        Country country = countryRepository.findById(dto.address().countryId()).orElseThrow(() -> new RuntimeException("Country not found"));

        Business business = businessMapper.toEntity(dto);
        Address address = addressMapper.toEntity(dto.address());
        address.setCountry(country);

        // Attach relationships that required database lookups
        business.setSector(sector);
        business.setIndustry(industry);
        business.setAddress(address);

        Business saved = businessRepository.save(business);

        return businessMapper.toResponse(business);
    }

    //fetch all business
    public List<Business> getAllBusinesses() { return businessRepository.findAll(); }

    //get business by ID
    public Business getBusinessById(Long id) {
        return businessRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Business not found with id " + id));
    }

    //fetch all sectors
    public List<Sector> getAllSectors() {
        return sectorRepository.findAll();
    }

    // Fetch all industries for a sector by ID
    public List<Industry> getIndustriesBySectorId(Long sectorId) {
        return industryRepository.findBySectorId(sectorId);
    }

}
