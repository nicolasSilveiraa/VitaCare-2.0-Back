package org.vitacare.dtos.patient;

import java.time.LocalDate;

public record PaginacaoPacienteRequest(
        String nome,
        LocalDate dataNascimento,
        String cpf,
        StatusDoPaciente statusDoPaciente
) { }
