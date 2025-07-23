package org.vitacare.emergenciaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaPacienteRequest {

    private String diagnostico;
    private String prescricao;
}
