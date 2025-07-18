package org.vitacare.convenioservice.handlerExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.vitacare.dtos.common.ErrorResponse;
import org.vitacare.convenioservice.exceptions.convenioExceptions.ConvenioCadastradoExceptions;
import org.vitacare.convenioservice.exceptions.convenioExceptions.ConvenioNaoExisteException;
import org.vitacare.convenioservice.exceptions.planosExceptions.PlanoNaoExisteExceptions;

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

    @ExceptionHandler(PlanoNaoExisteExceptions.class)
    public ResponseEntity<ErrorResponse> planoNaoExiste(PlanoNaoExisteExceptions planoNaoExisteExceptions) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), planoNaoExisteExceptions.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
