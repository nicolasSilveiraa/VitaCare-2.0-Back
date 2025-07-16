package org.vitacare.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AgendaCreateRequest {

    private Long pacienteId;
    private LocalDate dataConsulta;
    private LocalTime horaConsulta;
    private String especialidade;
    private String medico;
    private String namePaciente;

}
