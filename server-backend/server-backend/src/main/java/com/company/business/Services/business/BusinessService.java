package com.company.business.Services.business;

import java.util.List;
import java.util.stream.Collectors;

//mappers
import com.company.business.Mappers.AddressMapper;
import com.company.business.Mappers.BusinessMapper;

//other
import com.company.business.dto.Business.response.BusinessResponseDTO;
import com.company.business.exceptions.InvalidIndustrySectorException;
import com.company.business.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//dto
import com.company.business.dto.Business.request.BusinessRequestDTO;

//models
import com.company.business.models.business.Address;
import com.company.business.models.business.Business;
import com.company.business.models.business.Industry;
import com.company.business.models.country.Country;

//repo
import com.company.business.repositories.business.BusinessRepository;
import com.company.business.repositories.Location.CountryRepository;
import com.company.business.repositories.business.IndustryRepository;

import jakarta.persistence.EntityNotFoundException;

//Service layer: contains the logic to get data, combine it, validate, etc.

@Service
public class BusinessService {

    private final IndustryRepository industryRepository;
    private final BusinessRepository businessRepository;
    private final CountryRepository countryRepository;

    private final AddressMapper addressMapper;
    private final BusinessMapper businessMapper;
    private final String businessName = "Business";


    @Autowired
    public BusinessService(BusinessRepository businessRepository, IndustryRepository industryRepository, CountryRepository countryRepository,
                           AddressMapper addressMapper, BusinessMapper BusinessMapper)
    {
        //repositories
        this.businessRepository = businessRepository;
        this.industryRepository = industryRepository;
        this.countryRepository = countryRepository;

        //mappers
        this.addressMapper = addressMapper;
        this.businessMapper = BusinessMapper;
    }

    public BusinessResponseDTO createBusiness(BusinessRequestDTO dto)
    {
        Industry industry = industryRepository.findById(dto.industryId()).orElseThrow(() -> new ResourceNotFoundException(dto.industryId(), "Industry"));
        Country country = countryRepository.findById(dto.address().countryId()).orElseThrow(() -> new RuntimeException("Country not found"));

        //Validate the Industry ID belongs to correct Sector ID
        validateIndustryBelongsToSector(industry, dto.sectorId());

        //toEntity
        Business business = businessMapper.toEntity(dto);
        Address address = addressMapper.toEntity(dto.address());

        // Attach relationships that required database lookups
        business.setIndustry(industry);
        address.setCountry(country);
        business.setAddress(address);

        //Save to Repo
        Business saved = businessRepository.save(business);

        return businessMapper.toResponse(saved);
    }

    public BusinessResponseDTO update(Long id, BusinessRequestDTO request) {

        Business existing = businessRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, businessName));
        Industry industry = industryRepository.findById(request.industryId()).orElseThrow(() -> new ResourceNotFoundException(request.industryId(), "Industry"));
        Country country = countryRepository.findById(request.address().countryId()).orElseThrow(() -> new ResourceNotFoundException(request.address().countryId(), "Country"));

        validateIndustryBelongsToSector(industry, request.sectorId());

        businessMapper.updateEntityFromDto(request, existing);     // Update normal Business fields from the request
        addressMapper.updateEntityFromDto(request.address(), existing.getAddress());   // Update the existing Address entity

        // Update relationship
        existing.setIndustry(industry);    // Update Industry relationship
        existing.getAddress().setCountry(country); // Update Country relationship on the Address

        Business saved = businessRepository.save(existing);

        return businessMapper.toResponse(saved);
    }

    /**
     * Deletes a Business with the specified ID.
     *
     * @param id the ID of the business to delete
     * @throws ResourceNotFoundException if an business with the specified ID does not exist
     */
    public void deleteBusiness(Long id)
    {
        Business business = businessRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, businessName));
        businessRepository.delete(business);
    }




    //fetch all business
    public List<BusinessResponseDTO> getAllBusinesses()
    {
        return businessRepository.findAll()
            .stream() //turns the list into a stream so we can process each item
            .map(businessMapper::toResponse) //converts EACH Address entity into an AddressResponseDTO
            .collect(Collectors.toList()); // turns it back into a List<AddressResponseDTO>
    }

    //get business by ID
    public Business getBusinessById(Long id) {
        return businessRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Business not found with id " + id));
    }


    /**
     * Validates that the selected industry belongs to the selected sector.
     * Throws an InvalidIndustrySectorException if the industry is associated
     * with a different sector.
     */
    private void validateIndustryBelongsToSector(
        Industry industry,
        Long sectorId) {

        if (!industry.getSector().getId().equals(sectorId)) {
            throw new InvalidIndustrySectorException(
                industry.getId(),
                sectorId
            );
        }
    }
}
