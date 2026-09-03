package com.company.business.Services.business;

import com.company.business.Mappers.SectorMapper;
import com.company.business.dto.Business.request.SectorRequestDTO;
import com.company.business.dto.Business.response.SectorResponseDTO;
import com.company.business.dto.Business.summary.SectorSummaryDTO;
import com.company.business.exceptions.ResourceNotFoundException;
import com.company.business.models.business.Sector;
import com.company.business.repositories.business.SectorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SectorService
{
    private final SectorRepository sectorRepository;
    private final SectorMapper sectorMapper;

    private final String sectorName = "Sector";

    public SectorService(SectorRepository sectorRepository, SectorMapper sectorMapper)
    {
        this.sectorRepository = sectorRepository;
        this.sectorMapper = sectorMapper;
    }

    /**
     * Creates a new sector
     *
     * @param dto the sector details
     * @return the created sector
     */
    public SectorResponseDTO createSector(SectorRequestDTO dto)
    {
        Sector sector = sectorMapper.toEntity(dto);
        Sector saved = sectorRepository.save(sector);
        return sectorMapper.toResponse(saved);
    }

    /**
     * Updates an existing Sector's details (name).
     *
     * @param id the ID of the sector to update
     * @param request the updated sector details
     * @return the updated sector dto
     * @throws ResourceNotFoundException if the sector does not exist
     */
    public SectorResponseDTO update(Long id, SectorRequestDTO request) {

        Sector existing = sectorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id, sectorName));

        existing.setName(request.name()); // change existing name to request name

        Sector saved = sectorRepository.save(existing); // Make save to repository

        return sectorMapper.toResponse(saved); // return response as DTO
    }

    /**
     * Deletes a sector with the specified ID.
     *
     * @param id the ID of the sector to delete
     * @throws ResourceNotFoundException if an sector with the specified ID does not exist
     */
    public void deleteSector(Long id)
    {
        Sector sector = sectorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(id, sectorName));

        sectorRepository.delete(sector);
    }

    /**
     * Retrieves a sector by its unique ID.
     *
     * @param id the ID of the sector to retrieve
     * @return the sector matching the specified ID along with all its industries
     * @throws ResourceNotFoundException if a sector with the specified ID does not exist
     */
    public SectorResponseDTO getSectorByIdWithIndustries(Long id)
    {
        return sectorRepository.findById(id)
            .map(sectorMapper::toResponse)
            .orElseThrow(() -> new ResourceNotFoundException(id, sectorName));
    }

    /**
     * Retrieves a sector by its unique ID.
     *
     * @param id the ID of the sector to retrieve
     * @return the sector matching the specified ID
     * @throws ResourceNotFoundException if a sector with the specified ID does not exist
     */
    public SectorSummaryDTO getSectorById(Long id)
    {
        return sectorRepository.findById(id)
            .map(sectorMapper::toResponseSummary)
            .orElseThrow(() -> new ResourceNotFoundException(id, sectorName));
    }


    /**
     * Retrieves all sectors with their industries
     *
     * @return a list containing all sectors and their industries
     */
    public List<SectorResponseDTO> getAllSectorsWithIndustries()
    {
        return sectorRepository.findAll()
            .stream() //turns the list into a stream so we can process each item
            .map(sectorMapper::toResponse) //converts EACH Address entity into an AddressResponseDTO
            .collect(Collectors.toList()); // turns it back into a List<AddressResponseDTO>
    }

    /**
     * Retrieves all sectors.
     *
     * @return a list containing all sectors
     */
    public List<SectorSummaryDTO> getAllSectors()
    {
        return sectorRepository.findAll()
            .stream()
            .map(sectorMapper::toResponseSummary)
            .collect(Collectors.toList());
    }

    /**
     * Retrieves a sector by name, ignoring differences in letter case along with all its industries
     *
     * @param name the name of the sector to retrieve
     * @return the sector matching the specified name along with its industries
     * @throws ResourceNotFoundException if a sector with the specified name does not exist
     */
    public SectorResponseDTO getSectorByNameWithIndustries(String name)
    {
        return sectorRepository.findByNameIgnoreCase(name)
            .map(sectorMapper::toResponse)
            .orElseThrow(() -> new ResourceNotFoundException(name, sectorName));
    }

    /**
     * Retrieves a sector by name, ignoring differences in letter case.
     *
     * @param name the name of the sector to retrieve
     * @return the sector matching the specified name
     * @throws ResourceNotFoundException if a sector with the specified name does not exist
     */
    public SectorSummaryDTO getSectorByName(String name)
    {
        return sectorRepository.findByNameIgnoreCase(name)
            .map(sectorMapper::toResponseSummary)
            .orElseThrow(() -> new ResourceNotFoundException(name, sectorName));
    }
}
