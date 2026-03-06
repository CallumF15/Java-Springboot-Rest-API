package uk.gov.hmcts.reform.dev.controllers.business;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.hmcts.reform.dev.Services.business.BusinessService;
import uk.gov.hmcts.reform.dev.models.business.Industry;
import uk.gov.hmcts.reform.dev.models.business.Sector;

import java.util.List;

@RestController
@RequestMapping("/api/business") //will change this later
public class BusinessController {

    private final BusinessService service;

    public BusinessController(BusinessService service) {
        this.service = service;
    }

    @GetMapping("/sectors")
    public List<Sector> getAllSectors() {
        return service.getAllSectors();
    }

    @GetMapping("industries/{sectorId}")
    public List<Industry> getIndustries(@PathVariable Long sectorId) {
        return service.getIndustriesBySectorId(sectorId);
    }
}
