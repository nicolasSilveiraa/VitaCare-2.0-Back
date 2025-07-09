package org.vitacare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vitacare.model.Enum.StatusDoPaciente;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginacaoPacienteRequest {

    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private StatusDoPaciente statusDoPaciente;


}
