package org.vitacare.dtos.patient;

import java.time.LocalDate;

public record PacienteResponse(
        Long pacienteId,
        String nomePaciente,
        LocalDate dataNascimento,
        SexoPaciente sexoPaciente,
        String endereco,
        Boolean convenio,
        Long idPlano,
        String cpf
) {
}
