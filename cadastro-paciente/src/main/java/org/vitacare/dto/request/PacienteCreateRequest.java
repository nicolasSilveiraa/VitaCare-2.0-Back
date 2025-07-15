package org.vitacare.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vitacare.model.Enum.SexoPaciente;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteCreateRequest {

    private String nomePaciente;
    private LocalDate dataNascimento;
    private SexoPaciente sexoPaciente;
    private String endereco;
    private Boolean convenio;
    private Long nomePlano;
    private String cpf;

}
