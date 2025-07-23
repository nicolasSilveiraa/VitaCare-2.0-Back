package org.vitacare.dtos.patient.emergencia;

import org.vitacare.dtos.patient.PacienteSummaryDTO;

import java.time.LocalDateTime;

public record FilaAtendimentoResponse(
        Long atendimentoId,
        LocalDateTime dataHoraChegada,
        PacienteSummaryDTO paciente
) {
}
