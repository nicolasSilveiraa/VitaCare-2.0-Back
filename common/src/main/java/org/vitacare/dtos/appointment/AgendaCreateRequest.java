package org.vitacare.dtos.appointment;

import java.time.LocalDate;
import java.time.LocalTime;

public record AgendaCreateRequest(
    Long pacienteId,
    LocalDate dataConsulta,
    LocalTime horaConsulta,
    String especialidade,
    String medico,
    String namePaciente,
    LocalTime criadoEm
){ }
