package org.vitacare.model;

import jakarta.persistence.*;
import lombok.*;
import org.vitacare.model.Enum.ConvenioDoPaciente;
import org.vitacare.model.Enum.EstadoCivilDoPaciente;
import org.vitacare.model.Enum.SexoDoPaciente;
import org.vitacare.model.Enum.StatusDoPaciente;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "naturalidade")
    private String naturalidadePaciente;

    @Column(name = "sexo")
    @Enumerated(EnumType.STRING)
    private SexoDoPaciente sexoPaciente;

    @Column(name = "estado_civil")
    @Enumerated(EnumType.STRING)
    private EstadoCivilDoPaciente estadoCivilPaciente;

    @Column(name = "cpf")
    private String cpfPaciente;

    @Column(name = "rg")
    private String rgPaciente;

    @Column(name = "orgao_emissor_rg")
    private String emissorRgPaciente;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "convenio")
    @Enumerated(EnumType.STRING)
    private ConvenioDoPaciente convenioCliente;

    @Column(name = "nome_plano")
    private String planoPaciente;

    @Column(name = "validade_convenio")
    private LocalDate validadeConvenio;

    @Column(name = "numero_carteirinha")
    private String carteirinhaPaciente;

    @Column(name = "alergias")
    private String alergiasPaciente;

    @Column(name = "queixas")
    private String queixasPaciente;

    @Column(name = "diagnostico")
    private String diagnosticoPaciente;

    @Column(name = "prescricao")
    private String prescricaoPaciente;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusDoPaciente statusPaciente;
}
