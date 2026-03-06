package uk.gov.hmcts.reform.dev.repositories.business;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.hmcts.reform.dev.models.business.Industry;

import java.util.List;

public interface IndustryRepository extends JpaRepository<Industry, Long> {

    List<Industry> findBySectorId(Long sectorId); //spring auto gens SQL query
}
