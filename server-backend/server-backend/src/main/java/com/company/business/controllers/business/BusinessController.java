package com.company.business.controllers.business;

import com.company.business.dto.Business.request.BusinessRequestDTO;
import com.company.business.dto.Business.response.BusinessResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.business.Services.business.BusinessService;

import com.company.business.models.business.Business;
import com.company.business.models.business.Industry;
import com.company.business.models.business.Sector;

import java.util.List;

@RestController
@RequestMapping("/api/business") //will change this later
public class BusinessController {

    private final BusinessService businessService;


    public BusinessController(BusinessService service)
    {
        this.businessService = service;
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
    public ResponseEntity<BusinessResponseDTO> createBusiness(@Valid @RequestBody BusinessRequestDTO dto) {

        BusinessResponseDTO business = businessService.createBusiness(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(business);
    }

    @GetMapping("all")
    public List<Business> getAll() {
        return businessService.getAllBusinesses();
    }


}
