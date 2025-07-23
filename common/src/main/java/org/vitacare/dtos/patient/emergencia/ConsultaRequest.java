package org.vitacare.dtos.patient.emergencia;

import jakarta.validation.constraints.NotBlank;

public record ConsultaRequest(
        @NotBlank String diagnostico,
        @NotBlank String prescricao
) { }
