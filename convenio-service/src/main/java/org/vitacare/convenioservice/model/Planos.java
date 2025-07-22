package org.vitacare.convenioservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_convenio")
    @JsonBackReference
    private ConvenioModel convenioModel;

    @ManyToMany
    @JoinTable(
            name = "plano_especialidade",
            joinColumns = @JoinColumn(name = "plano_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidade_id")
    )
    private List<Especialidade> especialidades;


}
