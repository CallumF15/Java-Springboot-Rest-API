package uk.gov.hmcts.reform.dev.Services.business;

import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.dev.models.business.Industry;
import uk.gov.hmcts.reform.dev.models.business.Sector;
import uk.gov.hmcts.reform.dev.repositories.business.IndustryRepository;
import uk.gov.hmcts.reform.dev.repositories.business.SectorRespository;

import java.util.List;

@Service
public class BusinessService {

    // ========================
    // Fields
    // ========================

    private final SectorRespository sectorRespository;
    private final IndustryRepository industryRepository;

    public BusinessService(SectorRespository sectorRespository, IndustryRepository industryRepository) {
        this.sectorRespository = sectorRespository;
        this.industryRepository = industryRepository;
    }

    public List<Sector> getAllSectors() {
        return sectorRespository.findAll();
    }

    public List<Industry> getIndustriesBySectorId(Long sectorId) {
        return industryRepository.findBySectorId(sectorId);
    }
}
