package com.company.business.repositories.Location;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.company.business.models.country.Country;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    //List<Country> findByName(String name);

    Optional<Country> findByName(String name);


    List<Country> findByCode(String code);
}
