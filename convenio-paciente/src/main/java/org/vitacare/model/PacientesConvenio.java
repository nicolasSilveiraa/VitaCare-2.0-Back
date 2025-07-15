package org.vitacare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pacientes_convenio")
public class PacientesConvenio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long pacienteId; //O Id do paciente que vai vir do outro microsserviço

    @Column(name = "data_inicio")
    private LocalDate dataDeInicio;

    @Column(name = "data_fim")
    private LocalDate dataDeFim;

    @Column(name = "ativo")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "plano_id")
    private Planos planos;



}
