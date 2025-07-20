package org.vitacare.patientservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.vitacare.patientservice.dto.ErrorResponse;
import org.vitacare.patientservice.exception.PacienteRegistradoException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PacienteRegistradoException.class)
    public ResponseEntity<ErrorResponse> pacienteJaRegistrado(PacienteRegistradoException pacinte) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), pacinte.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }

}
