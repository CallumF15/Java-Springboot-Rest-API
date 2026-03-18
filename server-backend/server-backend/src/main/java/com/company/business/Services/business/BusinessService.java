package com.company.business.Services.business;

import com.company.business.models.business.Business;
import com.company.business.repositories.business.BusinessRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.company.business.models.business.Industry;
import com.company.business.models.business.Sector;
import com.company.business.repositories.business.IndustryRepository;
import com.company.business.repositories.business.SectorRepository;

import java.util.List;

//Service layer: contains the logic to get data, combine it, validate, etc.

@Service
public class BusinessService {

    private final SectorRepository sectorRespository;
    private final IndustryRepository industryRepository;
    private final BusinessRepository businessRepository;

    @Autowired
    public BusinessService(BusinessRepository businessRepository, SectorRepository sectorRespository, IndustryRepository industryRepository) {
        this.businessRepository = businessRepository;
        this.sectorRespository = sectorRespository;
        this.industryRepository = industryRepository;
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
