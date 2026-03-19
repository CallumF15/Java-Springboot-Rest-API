package com.company.business.controllers.business;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.business.Services.business.BusinessService;

import com.company.business.models.Task;
import com.company.business.models.business.Business;
import com.company.business.models.business.Industry;
import com.company.business.models.business.Sector;

import com.company.business.repositories.business.BusinessRepository;
import com.company.business.repositories.business.IndustryRepository;
import com.company.business.repositories.business.SectorRepository;

import java.util.List;

@RestController
@RequestMapping("/api/business") //will change this later
public class BusinessController {

    private final BusinessService businessService;

    private final BusinessRepository businessRepository;
    private final SectorRepository sectorRepository;
    private final IndustryRepository industryRepository;

    public BusinessController(BusinessService service, BusinessRepository businessRepository, SectorRepository sectorRepository, IndustryRepository industryRepository) {
        this.businessService = service;
        this.businessRepository = businessRepository;
        this.sectorRepository = sectorRepository;
        this.industryRepository = industryRepository;
    }

    @GetMapping("/sectors")
    public List<Sector> getAllSectors() {
        return businessService.getAllSectors();
    }

    @GetMapping("industries/{sectorId}")
    public List<Industry> getIndustriesBySector(@PathVariable Long sectorId) {
        return businessService.getIndustriesBySectorId(sectorId);
    }

    @Operation(
        summary = "Create a new business",
        description = "Creates a new business with attributes",
        responses = {
            @ApiResponse(responseCode = "201", description = "business successfully created",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Business.class))),
            @ApiResponse(responseCode = "400", description = "Validation failed")
        }
    )
    @PostMapping
    public ResponseEntity<Business> createBusiness(@Valid @RequestBody Business dto) {
        // fetch sector and industry by IDs
        Sector sector = sectorRepository.findById(dto.getSector().getId()).orElseThrow(() -> new RuntimeException("Sector not found"));
        Industry industry = industryRepository.findById(dto.getIndustry().getId()).orElseThrow(() -> new RuntimeException("Industry not found"));

        //above returns null just now (fix)

        Business business = mapDtoToBusiness(dto, sector, industry);

        // save to DB
        Business saved = businessRepository.save(business);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("all")
    public List<Business> getAll() {
        return businessService.getAllBusinesses();
    }

    private Business mapDtoToBusiness(Business dto, Sector sector, Industry industry) {
        Business business = new Business();
        business.setTitle(dto.getTitle());
        business.setDescription(dto.getDescription());
        business.setSector(sector);
        business.setIndustry(industry);
        business.setEmail(dto.getEmail());
        business.setLandlineNumber(dto.getLandlineNumber());
        business.setPhoneNumber(dto.getPhoneNumber());
        business.setAddress(dto.getAddress());
        business.setWebsite(dto.getWebsite());
        business.setLogoUrl(dto.getLogoUrl());
        business.setIsActive(dto.getIsActive());

        return business;
    }
}
