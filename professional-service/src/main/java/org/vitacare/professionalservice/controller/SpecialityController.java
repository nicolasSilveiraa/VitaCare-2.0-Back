package org.vitacare.professionalservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.vitacare.professionalservice.dto.SpecialtyDetailDTO;
import org.vitacare.professionalservice.dto.SpecialtySummaryDTO;
import org.vitacare.professionalservice.service.SpecialtyService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/specialties")
@RequiredArgsConstructor
public class SpecialityController {

    private final SpecialtyService specialtyService;

    @GetMapping("/summary")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<SpecialtySummaryDTO>> getSpecialtySummaries() {
        return ResponseEntity.ok(specialtyService.findAllSummaries());
    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<SpecialtyDetailDTO> getSpecialtyById(@PathVariable Integer id) {
        return ResponseEntity.ok(specialtyService.findById(id));
    }

}
