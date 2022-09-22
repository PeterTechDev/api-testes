package com.petertech.api.resources.exceptions;

import com.petertech.api.services.exceptions.ObjetctNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjetctNotFoundException.class)
    public ResponseEntity<StandartError> objectNotFound(ObjetctNotFoundException exception, HttpServletRequest request) {
        StandartError error =
                new StandartError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), exception.getMessage(), request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
