package org.vitacare.dtos.patient;

import org.vitacare.dtos.patient.emergencia.StatusAtendimento;

import java.time.LocalDate;

public record PaginacaoPacienteRequest(
        String nome,
        LocalDate dataNascimento,
        String cpf,
        StatusAtendimento statusAtendimento
) { }
