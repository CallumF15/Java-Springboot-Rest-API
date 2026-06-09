package com.company.business.controllers.business;


import com.company.business.Services.business.CountryService;
import com.company.business.dto.Business.response.CountryResponseDTO;
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
@RequestMapping("/api/countries") //will change this later
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @Operation(
        summary = "Get all countries in the country table and display their id, name, code",
        description = "Returns a list of countries with id, names and codes",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all countries",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CountryResponseDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Validation failed")
        }
    )
    @GetMapping("/All")
    public ResponseEntity<List<CountryResponseDTO>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @Operation(
        summary = "Get all Countries Names by String",
        description = "Returns a list of all country names as strings",
        responses = {
            @ApiResponse(responseCode = "200", description = "List of country names retrieved",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(type = "string")))),
            @ApiResponse(responseCode = "400", description = "Validation failed")
        }
    )
    @GetMapping("/Names")
    public List<String> getAllCountryNames() { return countryService.getAllCountryNames(); }

    @Operation(
        summary = "Get all Countries Codes By String",
        description = "Returns a list of all country codes as strings",
        responses = {
            @ApiResponse(responseCode = "200", description = "List of country codes retrieved",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(type = "string")))),
            @ApiResponse(responseCode = "400", description = "Validation failed")
        }
    )
    @GetMapping("/Codes")
    public List<String> getCountryCodes() { return countryService.getAllCountryCodes(); }

    @Operation(
        summary = "Get a Country by its ID",
        description = "Returns a single country with its name and code by ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Country has been found",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CountryResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Country not found"),
            @ApiResponse(responseCode = "400", description = "Invalid country ID supplied"),
            @ApiResponse(responseCode = "500", description = "Unexpected server error")

        }
    )
    @GetMapping("/{id}")
    public CountryResponseDTO getCountryById(@PathVariable Long id) { return countryService.getCountryById(id); }
}
