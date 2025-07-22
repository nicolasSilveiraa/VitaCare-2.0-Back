package org.vitacare.dtos.healthplan;

import java.util.List;

public record ConvenioComPlanosRequest(
        String nomeConvenio,
        String cnpjConvenio,
        List<PlanosRequest> planos
) {}