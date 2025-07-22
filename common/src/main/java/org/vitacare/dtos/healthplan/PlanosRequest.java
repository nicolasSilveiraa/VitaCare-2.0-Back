package org.vitacare.dtos.healthplan;

import java.util.List;

public record PlanosRequest(
        String nome,
        Long idConvenio,
        List<EspecialidadeEnum> especialidades
) { }
