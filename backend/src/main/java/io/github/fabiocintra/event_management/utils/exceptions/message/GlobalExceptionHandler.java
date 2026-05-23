package io.github.fabiocintra.event_management.utils.exceptions.message;

import io.github.fabiocintra.event_management.utils.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    @ExceptionHandler(EventRegisteredException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public CustomErrorMessage handleEventRegisteredException(EventRegisteredException e){
        return new CustomErrorMessage(
                HttpStatus.CONFLICT.value(),
                e.getMessage(),
                List.of()
        );
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorMessage handleNotFoundException(NotFoundException e) {
        return new CustomErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                List.of()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorMessage handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new CustomErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                "Request data has an invalid format!",
                List.of()
        );
    }

    @ExceptionHandler(MethodErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorMessage handleMethodErrorException(MethodErrorException e) {
        return new CustomErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                List.of()
        );
    }


    @ExceptionHandler(TicketCannotRegisteredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorMessage handleTicketCannotRegisteredException(TicketCannotRegisteredException e) {
        return new CustomErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                List.of()
        );
    }

    @ExceptionHandler(OrderItemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorMessage handleOrderItemException(OrderItemException e) {
        return new CustomErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                List.of()
        );
    }

    @ExceptionHandler(OrderInvalidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorMessage handleOrderInvalidException(OrderInvalidException e) {
        return new CustomErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                List.of()
        );
    }
}
