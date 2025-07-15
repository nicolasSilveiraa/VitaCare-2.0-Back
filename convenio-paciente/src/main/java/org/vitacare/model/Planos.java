package org.vitacare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "planos")
public class Planos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "tipo")
    private String tipoPlano;

    @Column(name = "ativo")
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "convenio_id")
    private ConvenioModel convenioModel;

    @OneToMany(mappedBy = "planosEspecialidade")
    private List<EspecialidadeDoPlano> especialidadeDoPlanoList;

    @OneToMany(mappedBy = "planos")
    private List<PacientesConvenio> pacientesConvenios;







}
