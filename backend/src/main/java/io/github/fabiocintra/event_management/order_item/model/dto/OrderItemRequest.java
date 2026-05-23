package io.github.fabiocintra.event_management.order_item.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderItemRequest(
        @NotNull(message = "Campo Obrigatorio!")
        @Min(value = 1, message = "Deve possuir no minimo 1 produto!")
        int quantity,
        @NotNull(message = "Campo Obrigatorio!")
        double unitPrice,
        @NotBlank(message = "Campo Obrigatorio!")
        String orderId,
        @NotBlank(message = "Campo Obrigatorio!")
        String ticketTypeId
) {
}
