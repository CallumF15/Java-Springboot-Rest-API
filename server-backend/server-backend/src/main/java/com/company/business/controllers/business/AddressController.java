package com.company.business.controllers.business;

import com.company.business.Services.business.AddressService;
import com.company.business.dto.Business.request.AddressRequestDTO;
import com.company.business.dto.Business.response.AddressResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address") //will change this later
public class AddressController {

    private final AddressService addressService;

//    public AddressController(AddressService addressService, CountryService countryService) {
//        this.addressService = addressService;
//        this.countryService = countryService;
//    }

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @Operation(
        summary = "Add new Address",
        description = "Creates a new address and returns the created address",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Address created successfully",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AddressRequestDTO .class)
                )
            ),
            @ApiResponse(responseCode = "400", description = "Invalid request")
        }
    )
    @PostMapping("/create")
    public AddressResponseDTO createAddress(@RequestBody AddressRequestDTO dto) {
        return addressService.createAddress(dto);
    }


    @Operation(
        summary = "Get all addresses in the address table",
        description = "Returns a list of addresses",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all countries",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AddressResponseDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Validation failed")
        }
    )
    @GetMapping("/All")
    public ResponseEntity<List<AddressResponseDTO>> getAllCountries() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }
}
