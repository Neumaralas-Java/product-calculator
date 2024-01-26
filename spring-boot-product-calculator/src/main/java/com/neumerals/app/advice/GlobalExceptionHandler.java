package com.neumerals.app.advice;

import com.neumerals.app.service.exception.BOException;
import com.neumerals.app.service.exception.DuplicateRecordException;
import com.neumerals.app.service.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BOException.class)
    public void handleRecordNotFoundException(BOException e) {
        ResponseStatusException exp;
        if (e instanceof RecordNotFoundException){
            exp =  new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }else if (e instanceof DuplicateRecordException){
            exp = new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
        }else{
            exp = new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), e);
        }
        e.initCause(exp);
        throw e;
    }
}
