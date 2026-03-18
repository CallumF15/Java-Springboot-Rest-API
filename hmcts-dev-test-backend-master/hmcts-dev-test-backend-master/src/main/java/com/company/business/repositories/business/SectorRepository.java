package com.company.business.repositories.business;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.business.models.business.Sector;

public interface SectorRepository extends JpaRepository<Sector, Long> {


    // no methods needed, JpaRepository already provides:
    // findAll(), findById(), save(), delete(), etc.

    //Spring Data JPA automatically provides implementations for common database operations
    //If needed, add my own custom queries here.
}
