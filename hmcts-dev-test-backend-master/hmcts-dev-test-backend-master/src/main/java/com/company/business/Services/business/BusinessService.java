package com.company.business.Services.business;

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

    @Autowired
    public BusinessService(SectorRepository sectorRespository, IndustryRepository industryRepository) {
        this.sectorRespository = sectorRespository;
        this.industryRepository = industryRepository;
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
