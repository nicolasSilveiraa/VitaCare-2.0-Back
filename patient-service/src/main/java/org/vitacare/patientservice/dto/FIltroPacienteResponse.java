package org.vitacare.patientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vitacare.patientservice.model.Enum.StatusDoPaciente;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FIltroPacienteResponse {

    private Long idPaciente;
    private String nomePaciente;
    private LocalDate dataNascimento;
    private String cpfPaciente;
    private StatusDoPaciente statusDoPaciente;
}
