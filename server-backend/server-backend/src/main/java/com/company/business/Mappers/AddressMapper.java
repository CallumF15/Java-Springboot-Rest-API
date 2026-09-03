package com.company.business.Mappers;

import com.company.business.dto.Business.request.AddressRequestDTO;
import com.company.business.dto.Business.request.BusinessRequestDTO;
import com.company.business.dto.Business.response.AddressResponseDTO;
import com.company.business.dto.Business.response.BusinessResponseDTO;
import com.company.business.models.business.Address;
import com.company.business.models.business.Business;
import com.company.business.models.country.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

///MapStruct mapper contract
///convert between your database model (Entity) and your API models (DTOs).


@Mapper( //Generate this mapper as a Spring bean so it can be injected with dependency injection.
    componentModel = "spring",
    uses = CountryMapper.class
)
public interface AddressMapper extends BaseMapper<Address, AddressRequestDTO, AddressResponseDTO>{

    @Override
    @Mapping(source = "countryId", target = "country")
    @Mapping(target = "id", ignore = true)
    Address toEntity(AddressRequestDTO dto);

//    @Override
//    @Mapping(source = "country.id", target = "countryId")
//    AddressRequestDTO toRequest(Address address);

    @Override
    AddressResponseDTO toResponse(Address address);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "country", ignore = true)
    void updateEntityFromDto(
        AddressRequestDTO dto,
        @MappingTarget Address address
    );


    default Country map(Long countryId) {
        if (countryId == null) return null;
        Country c = new Country();
        c.setId(countryId);
        return c;
    }
}

