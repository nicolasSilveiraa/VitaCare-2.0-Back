package org.vitacare.handlerExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.vitacare.dto.response.ErrorResponse;
import org.vitacare.exceptions.convenioExceptions.ConvenioCadastradoExceptions;
import org.vitacare.exceptions.convenioExceptions.ConvenioNaoExisteException;

@RestControllerAdvice
public class GlobalHandlerExceptions {

    @ExceptionHandler(ConvenioNaoExisteException.class)
    public ResponseEntity<ErrorResponse> convenioNaoExiste(ConvenioNaoExisteException convenioNaoExisteException) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), convenioNaoExisteException.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConvenioCadastradoExceptions.class)
    public ResponseEntity<ErrorResponse> convenioJaCadastrado(ConvenioCadastradoExceptions convenioCadastradoExceptions) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT.value(), convenioCadastradoExceptions.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

}
