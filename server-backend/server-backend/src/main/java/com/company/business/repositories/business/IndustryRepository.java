package com.company.business.repositories.business;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.business.dto.Business.response.IndustryResponseDTO;
import com.company.business.dto.Business.response.SectorResponseDTO;
import com.company.business.models.business.Industry;
import com.company.business.models.business.Sector;

import java.util.List;

public interface IndustryRepository extends JpaRepository<Industry, Long> {

    //Spring Data JPA automatically provides implementations for common database operations
    //If needed, add my own custom queries here.

    public default SectorResponseDTO mapToResponseDTO(Sector sector) {
        List<IndustryResponseDTO> industries = sector.getIndustries().stream()
            .map(industry -> new IndustryResponseDTO(
                industry.getId(),
                industry.getName(),
                sector.getId(),
                sector.getName()
            ))
            .toList();

        return new SectorResponseDTO(sector.getId(), sector.getName(), industries);
    }

    //filter by sector
    List<Industry> findBySectorId(Long sectorId); //spring auto gens SQL query
}
