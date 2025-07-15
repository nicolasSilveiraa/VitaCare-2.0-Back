package org.vitacare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "planos_cobertura_especialidades")
public class EspecialidadeDoPlano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "especialidade")
    private String especialidade;

    @ManyToOne
    @JoinColumn(name = "plano_id")
    private Planos planosEspecialidade;



}
