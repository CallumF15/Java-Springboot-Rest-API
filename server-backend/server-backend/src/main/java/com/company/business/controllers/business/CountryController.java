package com.company.business.controllers.business;


import com.company.business.Services.business.CountryService;
import com.company.business.dto.Business.response.CountryResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
        summary = "Get all countries",
        description = "Returns a list of countries with names",
        responses = {
            @ApiResponse(responseCode = "200", description = "List of country objects",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = CountryResponseDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Validation failed")
        }
    )
    @GetMapping("/NameByDto")
    public List<CountryResponseDTO> getCountries() {
        return countryService.getAllCountriesNames();
    }

    @Operation(
        summary = "Get all Countries Names By String",
        description = "Returns a list of all country names as strings",
        responses = {
            @ApiResponse(responseCode = "200", description = "List of country names retrieved",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(type = "string")))),
            @ApiResponse(responseCode = "400", description = "Validation failed")
        }
    )
    @GetMapping("/NamebyString")
    public List<String> getCountryNames() { return countryService.getAllCountryNamesAsStrings(); }

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
    public List<String> getCountryCodes() { return countryService.getAllCountryCodesAsStrings(); }

    @Operation(
        summary = "Get Country by ID",
        description = "Returns a single country with its name and code by ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Country found",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CountryResponseDTO.class))
            ),
            @ApiResponse(responseCode = "404", description = "Country not found")
        }
    )
    @GetMapping("/{id}")
    public CountryResponseDTO getCountryById(@PathVariable Long id) { return countryService.getCountryById(id); }
}
