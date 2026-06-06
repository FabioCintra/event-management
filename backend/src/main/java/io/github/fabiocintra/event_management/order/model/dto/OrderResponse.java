package io.github.fabiocintra.event_management.order.model.dto;

import io.github.fabiocintra.event_management.order.model.OrderStatus;
import io.github.fabiocintra.event_management.order_item.model.OrderItem;
import io.github.fabiocintra.event_management.ticket.model.Ticket;

import java.time.Instant;
import java.util.List;

public record OrderResponse(
        String id,
        Double totalAmount,
        Instant createdAt,
        Instant paidAt,
        OrderStatus status
) {
}
