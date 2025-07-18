package org.vitacare.convenioservice.model;

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
    private ConvenioModel convenioModel;

    @OneToMany(mappedBy = "planos")
    @JsonIgnore
    private List<EspecialidadeDoPlanoModel> especialidadeDoPlanos;

}
