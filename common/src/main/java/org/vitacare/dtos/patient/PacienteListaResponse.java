package org.vitacare.dtos.patient;

import java.time.LocalDate;

public record PacienteListaResponse(
        Long idPaciente,
        String nomePaciente,
        String cpf,
        LocalDate dataNascimento
) { }
