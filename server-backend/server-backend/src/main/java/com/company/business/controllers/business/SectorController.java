package com.company.business.controllers.business;


import com.company.business.Services.business.SectorService;
import com.company.business.dto.Business.request.SectorRequestDTO;
import com.company.business.dto.Business.response.SectorResponseDTO;
import com.company.business.dto.Business.response.SectorResponseDTO;
import com.company.business.dto.Business.summary.SectorSummaryDTO;
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
@RequestMapping("/api/sector") //will change this later
public class SectorController
{
    private final SectorService sectorService;

    public SectorController(SectorService sectorService)
    {
        this.sectorService = sectorService;
    }

    @Operation(
        summary = "Creates a new Sector",
        description = "Creates a new sector using the provided sector details.",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Sector created successfully",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SectorRequestDTO.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Validation failed"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Sector not found"
            )
        }
    )
    @PostMapping("/create")
    public ResponseEntity<SectorResponseDTO> createSector(@RequestBody SectorRequestDTO request)
    {
        SectorResponseDTO sector = sectorService.createSector(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(sector);
    }

    @Operation(
        summary = "Update an sector",
        description = "Updates the sector matching the specified ID.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Sector successfully updated",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SectorResponseDTO.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Validation failed"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Sector not found"
            )
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<SectorResponseDTO> updateSector(@PathVariable Long id, @Valid @RequestBody SectorRequestDTO request)
    {
        return ResponseEntity.ok(sectorService.update(id, request));
    }

    @Operation(
        summary = "Delete an sector",
        description = "Deletes the sector matching the specified ID.",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "Sector successfully deleted"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Sector not found"
            )
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSector(@PathVariable Long id)
    {
        sectorService.deleteSector(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
        summary = "Get sector by ID",
        description = "Returns a sector matching the provided ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Sector found"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Sector not found"
            )
        })
    @GetMapping("/{id}")
    public ResponseEntity<SectorSummaryDTO> getSectorById(@PathVariable Long id)
    {
        return ResponseEntity.ok(sectorService.getSectorById(id));
    }

    @Operation(
        summary = "Get sector by ID along with all it industries",
        description = "Returns a sector matching the provided ID along with all it industries",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Sector found"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Sector not found"
            )
        })
    @GetMapping("/{id}/with-industries")
    public ResponseEntity<SectorResponseDTO> getSectorByIdWithIndustries(@PathVariable Long id)
    {
        return ResponseEntity.ok(sectorService.getSectorByIdWithIndustries(id));
    }


    @Operation(
        summary = "Get all sectors with industries",
        description = "Returns a list of sectors with all their industries",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved all sectors and their industries",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = SectorResponseDTO.class)))),
            @ApiResponse(
                responseCode = "400",
                description = "Validation failed")
        })
    @GetMapping("/sector/with-industries")
    public ResponseEntity<List<SectorResponseDTO>> getAllSectorsWithIndustries() {
        return ResponseEntity.ok(sectorService.getAllSectorsWithIndustries());
    }

    @Operation(
        summary = "Get all sectors",
        description = "Returns a list of sectors",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved all sectors",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = SectorResponseDTO.class)))),
            @ApiResponse(
                responseCode = "400",
                description = "Validation failed")
        })
    @GetMapping("/sector/all")
    public ResponseEntity<List<SectorSummaryDTO>> getAllSectors() {
        return ResponseEntity.ok(sectorService.getAllSectors());
    }

    @Operation(
        summary = "Get sector by name and all of its industries",
        description = "Returns a sector matching the provided name along with its industries",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved sector",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SectorResponseDTO.class)
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Sector not found"
            )
        }
    )
    @GetMapping("/with-industries-by-name")
    public ResponseEntity<SectorResponseDTO> getSectorByNameWithIndustries(@RequestParam String name) //requestParam makes url query parameter = GET /api/sectors/by-name?name=Technology
    {
        return ResponseEntity.ok(sectorService.getSectorByNameWithIndustries(name));
    }

    @Operation(
        summary = "Get sector by name",
        description = "Returns a sector matching the provided name",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved sector",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = SectorResponseDTO.class)
                )
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Sector not found"
            )
        }
    )
    @GetMapping("/by-name")
    public ResponseEntity<SectorSummaryDTO> getSectorByName(@RequestParam String name) //requestParam makes url query parameter = GET /api/sectors/by-name?name=Technology
    {
        return ResponseEntity.ok(sectorService.getSectorByName(name));
    }

}
