package org.vitacare.dtos.common;

public record ErrorResponse(
        int status,
        String mensagem
) {}
