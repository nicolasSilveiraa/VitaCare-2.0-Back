package org.vitacare.dtos.appointment;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendaCreateRequest(
        @NotNull Long pacienteId,
        @NotNull Long profissionalId,
        @NotNull Integer especialidadeId,
        @NotNull LocalDateTime dataHoraAgendamento
    ){ }
