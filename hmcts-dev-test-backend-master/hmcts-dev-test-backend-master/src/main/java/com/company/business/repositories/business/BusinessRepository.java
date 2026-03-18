package com.company.business.repositories.business;

import org.springframework.data.jpa.repository.JpaRepository;
import com.company.business.models.business.Business;

public interface BusinessRepository  extends JpaRepository<Business, Long> {

}
