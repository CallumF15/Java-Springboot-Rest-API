package com.company.business.Services.business;

import java.util.List;

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

    private final SectorRepository sectorRespository;
    private final IndustryRepository industryRepository;
    private final BusinessRepository businessRepository;
    private final CountryRepository countryRepository;

    @Autowired
    public BusinessService(BusinessRepository businessRepository, SectorRepository sectorRespository, IndustryRepository industryRepository, CountryRepository countryRepository) {
        this.businessRepository = businessRepository;
        this.sectorRespository = sectorRespository;
        this.industryRepository = industryRepository;
        this.countryRepository = countryRepository;
    }

    public Business createBusiness(BusinessRequestDTO dto) {

        // 🔍 1. Get country
        Long countryID = dto.address().countryId();

        Country country = countryRepository.findById(countryID)
            .orElseThrow(() -> new RuntimeException("Country not found"));

        // 🏗 2. Build address
        Address address = new Address();
        address.setStreet(dto.address().street());
        address.setCountry(country);

        // 🏢 3. Build business
        Business business = new Business();
        business.setTitle(dto.title());
        business.setAddress(address);

        return businessRepository.save(business);
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
        return sectorRespository.findAll();
    }

    // Fetch all industries for a sector by ID
    public List<Industry> getIndustriesBySectorId(Long sectorId) {
        return industryRepository.findBySectorId(sectorId);
    }
}
