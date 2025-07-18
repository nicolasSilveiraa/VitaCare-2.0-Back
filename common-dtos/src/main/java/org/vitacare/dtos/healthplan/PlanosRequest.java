package org.vitacare.convenioservice.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanosRequest {

    private String nome;
    private Long idConvenio;
    private List<Long> especialidadeDoPlano;
}
