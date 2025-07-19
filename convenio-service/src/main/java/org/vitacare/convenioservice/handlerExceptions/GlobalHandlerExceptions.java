package org.vitacare.convenioservice.handlerExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.vitacare.dtos.common.ErrorResponse;
import org.vitacare.convenioservice.exceptions.convenioExceptions.ConvenioCadastradoExceptions;
import org.vitacare.convenioservice.exceptions.convenioExceptions.ConvenioNaoExisteException;
import org.vitacare.convenioservice.exceptions.planosExceptions.PlanoNaoExisteExceptions;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalHandlerExceptions {

    @ExceptionHandler(ConvenioNaoExisteException.class)
    public ResponseEntity<ErrorResponse> convenioNaoExiste(ConvenioNaoExisteException convenioNaoExisteException, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Bad Request",
                convenioNaoExisteException.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConvenioCadastradoExceptions.class)
    public ResponseEntity<ErrorResponse> convenioJaCadastrado(ConvenioCadastradoExceptions convenioCadastradoExceptions, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Conflict",
                convenioCadastradoExceptions.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PlanoNaoExisteExceptions.class)
    public ResponseEntity<ErrorResponse> planoNaoExiste(PlanoNaoExisteExceptions planoNaoExisteExceptions, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                planoNaoExisteExceptions.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
