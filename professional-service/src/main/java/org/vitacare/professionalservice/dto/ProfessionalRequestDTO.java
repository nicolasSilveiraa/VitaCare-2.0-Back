package org.vitacare.professionalservice.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ProfessionalRequestDTO(
        @NotEmpty String fullName,
        @NotEmpty String professionalLicense,
        @NotEmpty List<Integer> specialtyIds
) { }
