package io.github.fabiocintra.event_management.ticket_type.model.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public record TicketTypeRequest(
        @NotBlank(message = "Campo Obrigatorio!")
        String name,
        @NotNull(message = "Campo Obrigatorio!")
        Double price,
        @NotNull(message = "Campo Obrigatorio!")
        Integer quantity,
        @NotNull(message = "Campo Obrigatorio!")
        String eventId,
        @Future
        @NotNull(message = "Campo Obrigatorio!")
        Timestamp saleEndsAt,
        @FutureOrPresent
        @NotNull(message = "Campo Obrigatorio!")
        Timestamp saleStartAt
) {
}
