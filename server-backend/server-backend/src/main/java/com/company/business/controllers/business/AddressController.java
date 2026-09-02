package com.company.business.controllers.business;

import com.company.business.Services.business.AddressService;
import com.company.business.dto.Business.request.AddressRequestDTO;
import com.company.business.dto.Business.response.AddressResponseDTO;
import com.company.business.dto.Business.response.BusinessResponseDTO;
import com.company.business.dto.Business.response.CountryResponseDTO;
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
@RequestMapping("/api/address") //will change this later
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService)
    {
        this.addressService = addressService;
    }

    @Operation(
        summary = "Creates a new Address",
        description = "Creates a new address using the provided address details.",
        responses = {
            @ApiResponse(
                responseCode = "201",
                description = "Address created successfully",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AddressRequestDTO .class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Validation failed"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Country not found"
            )
        }
    )
    @PostMapping("/create")
    public ResponseEntity<AddressResponseDTO> createAddress(@RequestBody AddressRequestDTO dto)
    {
        AddressResponseDTO business = addressService.createAddress(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(business);
    }

    @Operation(
        summary = "Update an address",
        description = "Updates the address matching the specified ID.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Address successfully updated",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AddressResponseDTO.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Validation failed"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Address or country not found"
            )
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressRequestDTO request)
    {
        return ResponseEntity.ok(addressService.update(id, request));
    }


    @Operation(
        summary = "Delete an address",
        description = "Deletes the address matching the specified ID.",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "Address successfully deleted"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Address not found"
            )
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id)
    {
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(
        summary = "Get all addresses in the address table",
        description = "Returns a list of addresses",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved all addresses",
                content = @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AddressResponseDTO.class)))),
            @ApiResponse(
                responseCode = "400",
                description = "Validation failed")
        })
    @GetMapping("/All")
    public ResponseEntity<List<AddressResponseDTO>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAllAddresses());
    }

    @Operation(
        summary = "Get an address by the ID provided",
        description = "returns the address by the specified ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved an address by ID",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AddressResponseDTO.class))),
            @ApiResponse(
                responseCode = "404",
                description = "Address not found")
        })

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> getAddressById(@PathVariable Long id)
    {
        return ResponseEntity.ok(addressService.getAddressById(id));
    }


    @Operation(
        summary = "Get an address by Postcode",
        description = "Returns a list of addresses by the specified postcode, ignoring letter cases",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved all addresses by specified postcode",
                content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AddressResponseDTO.class)))),
        })
    @GetMapping("/postcode/{postcode}")
    public ResponseEntity<List<AddressResponseDTO>> getAddressesByPostcode(@PathVariable String postcode)
    {
        return ResponseEntity.ok(addressService.getAddressesByPostcode(postcode));
    }

    @Operation(
        summary = "Get addresses by city",
        description = "Returns a list of addresses matching the specified city, ignoring letter case.",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved addresses by specified city",
                content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                        schema = @Schema(implementation = AddressResponseDTO.class))))
        })
    @GetMapping("/city/{city}")
    public ResponseEntity<List<AddressResponseDTO>> getAddressesByCity(@PathVariable String city)
    {
        return ResponseEntity.ok(addressService.getAddressesByCity(city));
    }
}
