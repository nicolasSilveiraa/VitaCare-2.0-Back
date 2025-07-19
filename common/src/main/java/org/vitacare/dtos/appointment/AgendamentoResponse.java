package org.vitacare.dtos.appointment;

import org.vitacare.dtos.patient.PacienteSummaryDTO;
import org.vitacare.dtos.professional.ProfessionalSummaryDTO;
import org.vitacare.dtos.professional.SpecialtySummaryDTO;

import java.time.LocalDateTime;

public record AgendamentoResponse(
        Long id,
        LocalDateTime agendamentoDateTime,
        PacienteSummaryDTO patient,
        ProfessionalSummaryDTO professional,
        SpecialtySummaryDTO specialty
        ) {
}
