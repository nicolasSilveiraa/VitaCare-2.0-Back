package org.vitacare.convenioservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vitacare.convenioservice.model.Enum.ConvenioDoPaciente;
import org.vitacare.convenioservice.model.Enum.StatusDoPaciente;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteCreateRequest {

    private String nomePaciente;
    private LocalDate dataNascimento;
    private String naturalidadePaciente;
    private String sexoPaciente;
    private String estadoCivilPaciente;
    private String cpfPaciente;
    private String rgPaciente;
    private String emissorRgPaciente;
    private String endereco;
    private ConvenioDoPaciente convenioCliente;
    private String planoPaciente;
    private LocalDate validadeConvenio;
    private String carteirinhaPaciente;
    private StatusDoPaciente statusPaciente;
}
