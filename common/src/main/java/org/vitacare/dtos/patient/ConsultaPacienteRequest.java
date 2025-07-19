package org.vitacare.dtos.patient;

public record ConsultaPacienteRequest(
        String diagnosticoPaciente,
        String prescricaoPaciente
) { }
