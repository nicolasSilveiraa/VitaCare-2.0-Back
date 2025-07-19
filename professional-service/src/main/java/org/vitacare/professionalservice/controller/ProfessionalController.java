package org.vitacare.professionalservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;
import org.vitacare.dtos.professional.ProfessionalRequestDTO;
import org.vitacare.dtos.professional.ProfessionalDetailDTO;
import org.vitacare.professionalservice.service.ProfessionalService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/professionals")
@EnableMethodSecurity
@RequiredArgsConstructor
class ProfessionalController {

    private final ProfessionalService professionalService;


    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST', 'ROLE_DOCTOR', 'ROLE_NURSE')")
    public ResponseEntity<List<ProfessionalDetailDTO>> getAllProfessionals(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer specialtyId ) {

        List<ProfessionalDetailDTO> professionals = professionalService.findProfessionals(name, specialtyId);
        return ResponseEntity.ok(professionals);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ProfessionalDetailDTO> getProfessionalById(@PathVariable Long id) {
        ProfessionalDetailDTO professional = professionalService.findProfessionalById(id);
        return ResponseEntity.ok(professional);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ProfessionalDetailDTO> createProfessional(@Valid @RequestBody ProfessionalRequestDTO requestDTO) {
        ProfessionalDetailDTO newProfessional = professionalService.createProfessional(requestDTO);
        return ResponseEntity.status(201).body(newProfessional);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<ProfessionalDetailDTO> patchProfessional(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        ProfessionalDetailDTO updatedProfessional = professionalService.patchProfessional(id, updates);
        return ResponseEntity.ok(updatedProfessional);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteProfessional(@PathVariable Long id) {
        professionalService.deleteProfessional(id);
        return ResponseEntity.noContent().build();
    }

}
