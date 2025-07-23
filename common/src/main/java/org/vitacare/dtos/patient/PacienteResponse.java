package org.vitacare.dtos.patient;

import org.vitacare.dtos.healthplan.ConvenioComPlanosResponse;

import java.time.LocalDate;

public record PacienteResponse(
        Long pacienteId,
        String nomePaciente,
        String cpf,
        LocalDate dataNascimento,
        SexoPaciente sexoPaciente,
        String endereco,
        Boolean convenio,
        ConvenioComPlanosResponse convenioComPlanosResponse
) {
}
