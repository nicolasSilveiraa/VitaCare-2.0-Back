package org.vitacare.pacientecomandoservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.vitacare.dtos.patient.SexoPaciente;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pacientes")
public class PacienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idPaciente;

    @Column(name = "nome")
    private String nomePaciente;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "sexo")
    @Enumerated(EnumType.STRING)
    private SexoPaciente sexoPaciente;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "possui_convenio")
    private Boolean convenio;

    @Column(name = "plano_id")
    private Long idPlano;

    @Column(name = "cpf")
    private String cpf;


}
