package org.vitacare.convenioservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TriagemPacienteRequest {

    private String alergiasPaciente;
    private String queixasPaciente;
}
