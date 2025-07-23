package org.vitacare.dtos.patient.emergencia;

import org.vitacare.dtos.patient.PacienteSummaryDTO;

import java.time.LocalDateTime;

public record AtendimentoEmergenciaResponse(
        Long id,
        LocalDateTime dataHoraChegada,
        StatusAtendimento status,
        String queixas,
        String alergias,
        String prescricao,
        String diagnostico,
        PacienteSummaryDTO paciente
) {
}
