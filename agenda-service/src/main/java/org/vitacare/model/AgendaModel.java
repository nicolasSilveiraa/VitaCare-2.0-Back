package org.vitacare.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agendamento")

public class AgendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idAgenda;

    @Column(name = "paciente_id")
    private Long pacienteId;

    @Column(name = "data_consulta")
    private LocalDate dataConsulta;

    @Column(name = "hora_consulta")
    private LocalTime horaConsulta;

    @Column(name = "especialidade")
    private String especialidade;

    @Column(name = "medico")
    private String medico;

    @Column(name = "name_paciente")
    private String namePaciente;

    @Column(name = "criado_em")
    private LocalTime criadoEm;


}
