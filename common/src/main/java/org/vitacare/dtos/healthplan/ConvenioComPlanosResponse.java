package org.vitacare.dtos.healthplan;

import java.util.List;

public record ConvenioComPlanosResponse(
        Long idConvenio,
        String nomeConvenio,
        String cnpjConvenio,
        List<PlanoComEspecialidadesResponse> planos
) {}