package org.vitacare.dtos.professional;

import java.util.List;

public record ProfessionalSummaryDTO(
        Long id,
        String fullName,
        List<String> specialtyNames
) {
}
