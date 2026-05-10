package io.github.fabiocintra.event_management.utils.exception;

public record FieldError (
        String fieldName,
        String menssage
){
}
