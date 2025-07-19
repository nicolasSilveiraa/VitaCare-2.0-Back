package org.vitacare.dtos.professional;

import java.util.List;

public record ProfessionalDetailDTO(
        Long id,
        String fullName,
        String professionalLicense,
        List<SpecialtySummaryDTO> specialties
) {}
