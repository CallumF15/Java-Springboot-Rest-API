package com.company.business.repositories.business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.company.business.models.country.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    // No extra methods needed for basic CRUD


}
