package com.company.business.repositories.business;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.business.models.business.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long>  {

    List<Address> findByPostcodeStartingWithIgnoreCase(String postcode);
    List<Address> findByCityIgnoreCase(String city);
}
