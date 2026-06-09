package com.company.business.controllers.Location;

import com.company.business.Services.Location.CityService;
import com.company.business.dto.Business.response.CityResponseDTO;
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
@RequestMapping("/api/City") //will change this later
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @Operation(
        summary = "Get all cities in the city table",
        description = "Returns a list of all cities",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved all cities",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CityResponseDTO.class)))),
            @ApiResponse(
                responseCode = "400",
                description = "An unexpected error occurred while retrieving cities")
        }
    )
    @GetMapping("/All")
    public ResponseEntity<List<CityResponseDTO>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    @Operation(
        summary = "Get a city by its ID",
        description = "Returns a single city",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "City found",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CityResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "City not found"),
            @ApiResponse(responseCode = "400", description = "Invalid city ID supplied"),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")
        }
    )
    @GetMapping("/{id}")
    public CityResponseDTO getCityById(@PathVariable Long id) { return cityService.getCityById(id); }
}
