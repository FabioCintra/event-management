package io.github.fabiocintra.event_management.utils.exceptions.message;

public record FieldError (
        String fieldName,
        String menssage
){
}
