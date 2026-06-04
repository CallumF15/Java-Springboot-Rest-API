package com.company.business.repositories.business;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.business.models.business.Address;

public interface AddressRepository extends JpaRepository<Address, Long>  {
    
}
