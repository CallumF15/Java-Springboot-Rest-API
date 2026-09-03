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

import java.util.List;

@RestController
@RequestMapping("/api/business") //will change this later
public class BusinessController
{
    private final BusinessService businessService;

    public BusinessController(BusinessService service)
    {
        this.businessService = service;
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


    @Operation(
        summary = "Update a business",
        description = "Updates an existing business using the provided business details",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Business updated successfully",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = BusinessResponseDTO.class)
                )
            ),
            @ApiResponse(
                responseCode = "400",
                description = "Validation failed or business does not belong to the selected sector"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Business, business, or country not found"
            )
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<BusinessResponseDTO> updateBusiness(@PathVariable Long id, @Valid @RequestBody BusinessRequestDTO request)
    {
        return ResponseEntity.ok(businessService.update(id, request));
    }

    @Operation(
        summary = "Delete an business",
        description = "Deletes the business matching the specified ID.",
        responses = {
            @ApiResponse(
                responseCode = "204",
                description = "business successfully deleted"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "business not found"
            )
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable Long id)
    {
        businessService.deleteBusiness(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<BusinessResponseDTO>> getAllBusinesses()
    {
        return ResponseEntity.ok(businessService.getAllBusinesses());
    }
}
