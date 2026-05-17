package io.github.fabiocintra.event_management.order.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderRequest(
        @NotBlank(message = "Campo obrigatorio!")
        String attendeeId,
        @NotNull(message = "Campo obrigatorio!")
        BigDecimal totalAmount
) {
}
