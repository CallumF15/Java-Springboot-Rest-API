package com.company.business.repositories.business;

import com.company.business.dto.Business.summary.IndustrySummaryDTO;
import com.company.business.dto.Business.summary.SectorSummaryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import com.company.business.dto.Business.response.IndustryResponseDTO;
import com.company.business.dto.Business.response.SectorResponseDTO;
import com.company.business.models.business.Industry;
import com.company.business.models.business.Sector;

import java.util.List;
import java.util.Optional;

public interface IndustryRepository extends JpaRepository<Industry, Long> {

    //Spring Data JPA automatically provides implementations for common database operations
    //If needed, add my own custom queries here.

    Optional<Industry> findByNameIgnoreCase(String name); //Why optional? This method might contain an Industry, or it might contain nothing.
    //filter by sector
    List<Industry> findBySectorId(Long sectorId); //spring auto gens SQL query

    List<Industry> findByNameContainingIgnoreCase(String name);
}
