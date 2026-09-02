package com.company.business.controllers.business;

import com.company.business.Services.business.IndustryService;
import com.company.business.dto.Business.request.IndustryRequestDTO;
import com.company.business.dto.Business.response.IndustryResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        summary = "Creates a new Industry",
        description = "Creates a new industry using the provided industry details.",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Industry created successfully",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = IndustryRequestDTO.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Validation failed"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Industry not found"
            )
        }
    )
    @PostMapping("/create")
    public ResponseEntity<IndustryResponseDTO> createIndustry(@RequestBody IndustryRequestDTO dto)
    {
        IndustryResponseDTO industry = industryService.createIndustry(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(industry);
    }

    @Operation(
        summary = "Update an industry",
        description = "Updates the industry matching the specified ID.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Industry successfully updated",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = IndustryResponseDTO.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Validation failed"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Industry not found"
            )
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<IndustryResponseDTO> updateIndustry(@PathVariable Long id, @Valid @RequestBody IndustryRequestDTO request)
    {
        return ResponseEntity.ok(industryService.update(id, request));
    }

    @Operation(
        summary = "Delete an industry",
        description = "Deletes the industry matching the specified ID.",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "Industry successfully deleted"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Industry not found"
            )
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIndustry(@PathVariable Long id)
    {
        industryService.deleteIndustry(id);
        return ResponseEntity.noContent().build();
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

    @Operation(
        summary = "Get an industry by name",
        description = "Returns the industry matching the specified name, ignoring letter case.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved industry",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = IndustryResponseDTO.class)
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Industry not found"
            )
        }
    )
    @GetMapping("/name/{name}")
    public ResponseEntity<IndustryResponseDTO> getIndustryByName(@PathVariable String name)
    {
        return ResponseEntity.ok(
            industryService.getIndustryByName(name)
        );
    }

    @Operation(
        summary = "Search industries by name",
        description = "Returns a list of industries whose names contain the specified value, ignoring letter case.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved matching industries",
                content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                        schema = @Schema(implementation = IndustryResponseDTO.class)
                    )
                )
            )
        }
    )
    @GetMapping("/search/{name}")
    public ResponseEntity<List<IndustryResponseDTO>> searchIndustriesByName(@PathVariable String name)
    {
        return ResponseEntity.ok(
            industryService.searchIndustriesByName(name)
        );
    }
}
