package org.vitacare.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "convenios")
public class ConvenioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idConvenio;

    @Column(name = "nome")
    private String nomeConvenio;

    @Column(name = "cnpj")
    private String cnpjConvenio;

    @OneToMany(mappedBy = "convenioModel")
    @JsonIgnore
    private List<Planos> planos;

}
