package uk.gov.hmcts.reform.dev.repositories.business;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.gov.hmcts.reform.dev.models.business.Sector;

public interface SectorRespository extends JpaRepository<Sector, Long> {

}
