package org.vitacare.dtos.healthplan;

import java.util.List;

public record PlanoComEspecialidadesResponse(
        Long id,
        String nome,
        List<EspecialidadeEnum> especialidades
) {}