package org.vitacare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vitacare.model.Enum.ConvenioDoPaciente;
import org.vitacare.model.Enum.StatusDoPaciente;

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
    private String alergiasPaciente;
    private String queixasPaciente;
    private String diagnosticoPaciente;
    private String prescricaoPaciente;
    private StatusDoPaciente statusPaciente;
}
