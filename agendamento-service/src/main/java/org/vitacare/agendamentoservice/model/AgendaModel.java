package org.vitacare.agendamentoservice.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agendamentos")

public class AgendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idAgenda;

    @Column(name = "profissionalId", nullable = false)
    private Long profissionalId;

    @Column(name = "paciente_id", nullable = false)
    private Long pacienteId;

    @Column(name = "especialidade_id", nullable = false)
    private Integer especialidadeId;

    @Column(name = "data_hora_agendamento", nullable = false)
    private LocalDateTime dataHoraAgendamento;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    public void prePersist() {
        if (criadoEm == null) {
            this.criadoEm = LocalDateTime.now().withSecond(0).withNano(0);
        }
    }


}
