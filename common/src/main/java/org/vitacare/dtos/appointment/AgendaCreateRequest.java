package org.vitacare.dtos.appointment;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendaCreateRequest(
        @NotNull Long id,
        @NotNull Long pacienteId,
        @NotNull Long professionalId,
        @NotNull Integer specialtyId,
        @NotNull LocalDateTime agendamentoDateTime
    ){ }
