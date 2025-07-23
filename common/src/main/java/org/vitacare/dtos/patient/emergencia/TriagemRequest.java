package org.vitacare.dtos.patient.emergencia;

import jakarta.validation.constraints.NotBlank;

public record TriagemRequest(
        @NotBlank String alergias,
        @NotBlank String queixas

) { }
