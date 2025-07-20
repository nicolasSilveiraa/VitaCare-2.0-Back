package org.vitacare.patientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaPacienteRequest {

    private String diagnosticoPaciente;
    private String prescricaoPaciente;
}
