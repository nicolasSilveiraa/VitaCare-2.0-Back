package org.vitacare.cadastroservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.vitacare.dtos.common.ErrorResponse;
import org.vitacare.cadastroservice.exception.PacienteCadastradoException;
import org.vitacare.cadastroservice.exception.PacienteExisteException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PacienteExisteException.class)
    public ResponseEntity<ErrorResponse> pacienteNaoEncontrado(PacienteExisteException paciente) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), paciente.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PacienteCadastradoException.class)
    public ResponseEntity<ErrorResponse> pacienteCadastrado(PacienteCadastradoException pacienteCadastradoException) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), pacienteCadastradoException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

}
