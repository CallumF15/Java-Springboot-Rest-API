package com.company.business.Services.business;

import com.company.business.Mappers.IndustryMapper;
import com.company.business.dto.Business.request.AddressRequestDTO;
import com.company.business.dto.Business.request.IndustryRequestDTO;
import com.company.business.dto.Business.response.AddressResponseDTO;
import com.company.business.exceptions.ResourceNotFoundException;
import com.company.business.models.business.Address;
import com.company.business.models.business.Sector;
import com.company.business.models.country.Country;
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

    private final String industryName = "Industry";
    private final String sectorName = "Sector";

    public IndustryService(IndustryRepository industryRepository, SectorRepository sectorRepository, IndustryMapper industryMapper) //dependency injection
    {
        this.industryRepository = industryRepository;
        this.sectorRepository = sectorRepository;
        this.industryMapper = industryMapper;
    }

    /**
     * Creates a new industry associated with the specified sector.
     *
     * @param dto the industry details, including the sector ID
     * @return the created industry
     * @throws ResourceNotFoundException if the specified sector does not exist
     */
    public IndustryResponseDTO createIndustry(IndustryRequestDTO dto)
    {
        Sector sector = sectorRepository.findById(dto.sectorId())
            .orElseThrow(() -> new ResourceNotFoundException(dto.sectorId(), sectorName));

        Industry industry = industryMapper.toEntity(dto);
        industry.setSector(sector);

        Industry saved = industryRepository.save(industry);
        return industryMapper.toResponse(saved);
    }

    /**
     * Updates an existing industry's details and associated sector.
     *
     * @param id the ID of the industry to update
     * @param request the updated industry details, including the sector ID
     * @return the updated industry
     * @throws ResourceNotFoundException if the industry or specified sector does not exist
     */
    public IndustryResponseDTO update(Long id, IndustryRequestDTO request) {

         Industry existing = industryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id, industryName));

        Sector sector = sectorRepository.findById(request.sectorId())
            .orElseThrow(() -> new ResourceNotFoundException(request.sectorId(), sectorName));

        existing.setName(request.name());
        existing.setSector(sector);

        Industry saved = industryRepository.save(existing);

        return industryMapper.toResponse(saved);
    }

    /**
     * Deletes an industry with the specified ID.
     *
     * @param id the ID of the industry to delete
     * @throws ResourceNotFoundException if an industry with the specified ID does not exist
     */
    public void deleteIndustry(Long id)
    {
        Industry industry = industryRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id, industryName));

        industryRepository.delete(industry);
    }

    public IndustryResponseDTO getIndustryById(Long id)
    {
        return industryRepository.findById(id)
            .map(industryMapper::toResponse)
            .orElseThrow(() -> new ResourceNotFoundException(id, industryName));
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
            .orElseThrow(() -> new ResourceNotFoundException(sectorId, sectorName));


        return industryRepository.findBySectorId(sectorId)
            .stream() //turns the list into a stream so we can process each item
            .map(industryMapper::toResponse) //converts EACH Address entity into an AddressResponseDTO
            .collect(Collectors.toList()); // turns it back into a List<AddressResponseDTO>
    }

    /**
     * Retrieves an industry by name, ignoring differences in letter case.
     *
     * @param name the name of the industry to retrieve
     * @return the industry matching the specified name
     * @throws ResourceNotFoundException if an industry with the specified name does not exist
     */
    public IndustryResponseDTO getIndustryByName(String name)
    {
        return industryRepository.findByNameIgnoreCase(name)
            .map(industryMapper::toResponse)
            .orElseThrow(() -> new ResourceNotFoundException(name, "Industry"));
    }

    /**
     * Searches for industries whose names contain the specified value,
     * ignoring differences in letter case.
     *
     * @param name the full or partial industry name to search for
     * @return a list of industries with names containing the specified value
     */
    public List<IndustryResponseDTO> searchIndustriesByName(String name)
    {
        return industryRepository
            .findByNameContainingIgnoreCase(name)
            .stream()
            .map(industryMapper::toResponse)
            .toList();
    }
}
