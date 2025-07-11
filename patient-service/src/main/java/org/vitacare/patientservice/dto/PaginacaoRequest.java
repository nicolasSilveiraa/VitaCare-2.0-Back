package org.vitacare.patientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vitacare.patientservice.model.Enum.StatusDoPaciente;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginacaoRequest {

    private Long idPaciente;
    private String nomePaciente;
    private LocalDate dataNascimento;
    private String cpf;
    private StatusDoPaciente statusDoPaciente;


}

//TODO Registro, Nome, Data de nascimento, CPF, status