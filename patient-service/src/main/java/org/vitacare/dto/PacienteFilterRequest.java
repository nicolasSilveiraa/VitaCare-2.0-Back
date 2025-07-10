package org.vitacare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vitacare.model.Enum.StatusDoPaciente;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PacienteFilterRequest {

    private String nomePaciente;
    private StatusDoPaciente statusDoPaciente;
    private Long id;
    private String cpf;
    private String search;
}
