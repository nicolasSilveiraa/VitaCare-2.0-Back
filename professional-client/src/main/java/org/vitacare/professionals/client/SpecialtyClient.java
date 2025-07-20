package org.vitacare.professionals.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.vitacare.dtos.professional.SpecialtySummaryDTO;

import java.util.List;

@FeignClient(name = "professional-service", contextId = "specialtyClient", path = "/api/v1/specialties")
public interface SpecialtyClient {

    @GetMapping("/summary")
    List<SpecialtySummaryDTO> getSpecialties();

    @GetMapping("/{id}/summary")
    SpecialtySummaryDTO getSpecialtySummaryById(@PathVariable("id") Integer id);
}
