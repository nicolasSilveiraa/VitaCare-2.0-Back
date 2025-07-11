package org.vitacare.patientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vitacare.patientservice.model.Enum.ConvenioDoPaciente;
import org.vitacare.patientservice.model.Enum.EstadoCivilDoPaciente;
import org.vitacare.patientservice.model.Enum.SexoDoPaciente;
import org.vitacare.patientservice.model.Enum.StatusDoPaciente;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteCreateRequest {

    private String nomePaciente;
    private LocalDate dataNascimento;
    private String naturalidadePaciente;
    private SexoDoPaciente sexoPaciente;
    private EstadoCivilDoPaciente estadoCivilPaciente;
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
