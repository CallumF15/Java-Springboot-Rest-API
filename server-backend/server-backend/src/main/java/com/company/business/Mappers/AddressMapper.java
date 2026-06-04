package com.company.business.Mappers;

import com.company.business.dto.Business.request.AddressRequestDTO;
import com.company.business.dto.Business.response.AddressResponseDTO;
import com.company.business.models.business.Address;
import com.company.business.models.country.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

///MapStruct mapper contract
///convert between your database model (Entity) and your API models (DTOs).

@Mapper(componentModel = "spring")
public interface AddressMapper{

    @Mapping(source = "country.id", target = "countryId")
    AddressRequestDTO toRequest(Address address);

    @Mapping(source = "country.id", target = "countryId")
    AddressResponseDTO toResponse(Address address);


    @Mapping(source = "countryId", target = "country")
    @Mapping(target = "id", ignore = true)
    Address toEntity(AddressRequestDTO dto);

    default Country map(Long countryId) {
        if (countryId == null) return null;
        Country c = new Country();
        c.setId(countryId);
        return c;
    }
}
