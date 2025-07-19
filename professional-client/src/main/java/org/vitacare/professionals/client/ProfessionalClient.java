package org.vitacare.professionals.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.vitacare.dtos.professional.ProfessionalSummaryDTO;

import java.util.List;

@FeignClient(name = "professional-service", path = "/api/v1/professionals")
public interface ProfessionalClient {

    @GetMapping
    List<ProfessionalSummaryDTO> findProfessionalsBySpecialty(@RequestParam("specialtyId") Integer specialtyId);

    @GetMapping("/api/v1/professionals/{id}/summary")
    ProfessionalSummaryDTO getProfessionalSummaryById(@PathVariable("id") Long id);
}
