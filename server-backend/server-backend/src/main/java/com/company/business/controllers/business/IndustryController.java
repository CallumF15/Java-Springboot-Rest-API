package com.company.business.controllers.business;

import com.company.business.Services.business.AddressService;
import com.company.business.Services.business.IndustryService;
import com.company.business.dto.Business.response.AddressResponseDTO;
import com.company.business.dto.Business.response.IndustryResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/industry") //will change this later
public class IndustryController {

    private final IndustryService industryService;

    public IndustryController(IndustryService industryService)
    {
        this.industryService = industryService;
    }

    @Operation(
        summary = "Get all industry in the industry table",
        description = "Returns a list of industries",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved all industries",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = IndustryResponseDTO.class)))),
            @ApiResponse(
                responseCode = "400",
                description = "Validation failed")
        })
    @GetMapping("/sector/all")
    public ResponseEntity<List<IndustryResponseDTO>> getAllIndustries() {
        return ResponseEntity.ok(industryService.getAllIndustries());
    }

    @GetMapping("/sector/{sectorId}")
    public ResponseEntity<List<IndustryResponseDTO>> getIndustriesBySector(@PathVariable Long sectorId)
    {
        return ResponseEntity.ok(
            industryService.getIndustriesBySector(sectorId)
        );
    }
}
