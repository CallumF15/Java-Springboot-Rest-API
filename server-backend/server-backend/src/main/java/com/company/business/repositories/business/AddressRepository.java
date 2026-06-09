package com.company.business.repositories.business;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.business.models.business.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long>  {

    List<Address> findAddressById(Long id);
}
