package io.github.fabiocintra.event_management.event.model.dto.event;

import io.github.fabiocintra.event_management.event.model.dto.location.LocationRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record EventRequest(
        @NotBlank(message = "Campo obrigatorio!")
        @Size(min=10,max=200, message = "Campo deve ter de 5 a 200 caracteres!")
        String title,
        @NotBlank(message = "Campo obrigatorio!")
        @Size(min=20,max=500, message = "Campo deve ter de 20 a 500 caracteres!")
        String description,
        @Future(message = "The event cannot start today!")
        @NotNull(message = "Campo obrigatorio!")
        LocalDateTime startsAt,
        @Future(message = "The event cannot finish today!")
        @NotNull(message = "Campo obrigatorio!")
        LocalDateTime endsAt,
        @NotNull(message = "Campo obrigatorio!")
        @Valid
        LocationRequest location,
        @NotBlank(message = "Campo obrigatorio!")
        String organizerId
) {
}
