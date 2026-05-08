package io.github.fabiocintra.event_management.utils.exception;

import io.github.fabiocintra.event_management.user.exceptions.EmailRegisteredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorMessage handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrorList = e.getFieldErrors()
                .stream()
                .map(error -> new FieldError(error.getField(), error.getDefaultMessage()))
                .toList();

        return new CustomErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                fieldErrorList
        );

    }

    @ExceptionHandler(EmailRegisteredException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public CustomErrorMessage handleEmailRegisteredException(EmailRegisteredException e){
        return new CustomErrorMessage(
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                List.of()
        );
    }

}
