package io.github.fabiocintra.event_management.utils.exception;

import java.util.List;

public record CustomErrorMessage(
        Integer status,
        String exceptionMessage,
        List<FieldError> fieldError
) {
}
