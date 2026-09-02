package com.company.business.Services.business;

import com.company.business.Mappers.IndustryMapper;
import com.company.business.dto.Business.response.AddressResponseDTO;
import com.company.business.dto.Business.summary.SectorSummaryDTO;
import com.company.business.exceptions.ResourceNotFoundException;
import com.company.business.repositories.business.IndustryRepository;
import com.company.business.repositories.business.SectorRepository;
import org.springframework.stereotype.Service;
import com.company.business.dto.Business.response.IndustryResponseDTO;
import com.company.business.models.business.Industry;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndustryService
{
    public IndustryRepository industryRepository;
    public SectorRepository sectorRepository;
    public IndustryMapper industryMapper;

    public IndustryService(IndustryRepository industryRepository, SectorRepository sectorRepository, IndustryMapper industryMapper) //dependency injection
    {
        this.industryRepository = industryRepository;
        this.sectorRepository = sectorRepository;
        this.industryMapper = industryMapper;
    }

    public IndustryResponseDTO getIndustryById(Long id)
    {
        return industryRepository.findById(id)
            .map(industryMapper::toResponse)
            .orElseThrow(() -> new ResourceNotFoundException(id, "Industry"));
    }

    public List<IndustryResponseDTO> getAllIndustries()
    {
        return industryRepository.findAll()
            .stream() //turns the list into a stream so we can process each item
            .map(industryMapper::toResponse) //converts EACH Address entity into an AddressResponseDTO
            .collect(Collectors.toList()); // turns it back into a List<AddressResponseDTO>
    }

    /**
     * Retrieves all industries associated with the specified sector.
     *
     * @param sectorId the ID of the sector
     * @return a list of industries belonging to the specified sector
     * @throws ResourceNotFoundException if the specified sector does not exist
     */
    public List<IndustryResponseDTO> getIndustriesBySector(Long sectorId)
    {
        sectorRepository.findById(sectorId)
            .orElseThrow(() -> new ResourceNotFoundException(sectorId, "Sector"));


        return industryRepository.findBySectorId(sectorId)
            .stream() //turns the list into a stream so we can process each item
            .map(industryMapper::toResponse) //converts EACH Address entity into an AddressResponseDTO
            .collect(Collectors.toList()); // turns it back into a List<AddressResponseDTO>
    }



    public IndustryResponseDTO mapToResponseDTO(Industry industry) {
        return new IndustryResponseDTO(
            industry.getId(),
            industry.getName(),
            new SectorSummaryDTO(
                industry.getSector().getId(),
                industry.getSector().getName()
            )
        );
    }
}
