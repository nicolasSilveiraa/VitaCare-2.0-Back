package org.vitacare.emergenciaservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.vitacare.dtos.patient.emergencia.StatusAtendimento;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "atendimentos_emergencia", schema = "emergencia_service")
public class AtendimentoEmergenciaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "paciente_id", nullable = false)
    private Long pacienteId;

    @Column(name = "queixas")
    private String queixas;

    @Column(name = "alergias")
    private String alergias;

    @Column(name = "diagnostico")
    private String diagnostico;

    @Column(name = "prescricao")
    private String prescricao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusAtendimento status;

    @CreationTimestamp
    @Column(name = "data_hora_chegada", nullable = false, updatable = false)
    private LocalDateTime dataHoraChegada;
}