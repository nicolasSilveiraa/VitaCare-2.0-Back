package org.vitacare.pacientecomandoservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.vitacare.dtos.common.ErrorResponse;
import org.vitacare.pacientecomandoservice.exception.PacienteCadastradoException;
import org.vitacare.pacientecomandoservice.exception.PacienteExisteException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PacienteExisteException.class)
    public ResponseEntity<ErrorResponse> pacienteNaoEncontrado(PacienteExisteException paciente,  WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                paciente.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PacienteCadastradoException.class)
    public ResponseEntity<ErrorResponse> pacienteCadastrado(PacienteCadastradoException ex,  WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflict",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
                );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

}
