package org.vitacare.dtos.healthplan;

public record PlanosResponse (
        Long id,
        String nome,
        Long idConvenio
){}
