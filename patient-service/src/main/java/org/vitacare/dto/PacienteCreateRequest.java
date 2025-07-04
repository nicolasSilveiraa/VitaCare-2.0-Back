package org.vitacare.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

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
    private Boolean convenioCliente;
    private String planoPaciente;
    private LocalDate validadeConvenio;
    private String carteirinhaPaciente;
    private String alergiasPaciente;
    private String queixasPaciente;
    private String diagnosticoPaciente;
    private String prescricaoPaciente;
    private String statusPaciente;
}
