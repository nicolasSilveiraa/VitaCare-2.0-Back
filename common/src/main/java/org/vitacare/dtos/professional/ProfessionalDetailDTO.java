package org.vitacare.dtos.professional;

import java.util.List;

public record ProfessionalResponseDTO(
        Long id,
        String fullName,
        String professionalLicense,
        List<SpecialtySummaryDTO> specialties
) {}
