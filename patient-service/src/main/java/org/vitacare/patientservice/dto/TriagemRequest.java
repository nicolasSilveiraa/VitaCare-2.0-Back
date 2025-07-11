package org.vitacare.patientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vitacare.patientservice.model.Enum.StatusDoPaciente;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TriagemRequest {

    private StatusDoPaciente statusDoPaciente;


}
