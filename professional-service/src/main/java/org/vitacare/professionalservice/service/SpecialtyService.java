package org.vitacare.professionalservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.vitacare.dtos.professional.SpecialtyDetailDTO;
import org.vitacare.dtos.professional.SpecialtySummaryDTO;
import org.vitacare.professionalservice.exception.ResourceNotFoundException;
import org.vitacare.professionalservice.model.Specialty;
import org.vitacare.professionalservice.repository.SpecialtyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public List<SpecialtySummaryDTO> findAllSummaries() {
        return specialtyRepository.findAll().stream()
                .map(this::convertToSummaryDTO)
                .collect(Collectors.toList());
    }

    public SpecialtyDetailDTO findById(Integer id) {
        Specialty specialty = findSpecialtyById(id);
        return convertToDetailDTO(specialty);
    }

    public SpecialtySummaryDTO findSummaryById(Integer id) {
        Specialty specialty = findSpecialtyById(id);
        return convertToSummaryDTO(specialty);
    }

    private SpecialtySummaryDTO convertToSummaryDTO(Specialty specialty) {
        return new SpecialtySummaryDTO(specialty.getId(), specialty.getName());
    }

    private SpecialtyDetailDTO convertToDetailDTO(Specialty specialty) {
        return new SpecialtyDetailDTO(specialty.getId(), specialty.getName(), specialty.getDescription());
    }

    private Specialty findSpecialtyById(Integer id) {
        return specialtyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada com o ID: " + id));
    }
}
