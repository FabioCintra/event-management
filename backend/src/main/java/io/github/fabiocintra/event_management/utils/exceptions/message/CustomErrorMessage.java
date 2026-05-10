package io.github.fabiocintra.event_management.utils.exceptions.message;

import java.util.List;

public record CustomErrorMessage(
        Integer status,
        String exceptionMessage,
        List<FieldError> fieldError
) {
}
