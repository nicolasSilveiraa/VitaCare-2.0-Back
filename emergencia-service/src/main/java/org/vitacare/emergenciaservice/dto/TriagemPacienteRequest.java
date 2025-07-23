package org.vitacare.emergenciaservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TriagemPacienteRequest {

    private String alergias;
    private String queixas;
}
