package org.vitacare.dtos.patient.emergencia;

import jakarta.validation.constraints.NotNull;

public record CheckInRequest(@NotNull Long pacienteId) { }
