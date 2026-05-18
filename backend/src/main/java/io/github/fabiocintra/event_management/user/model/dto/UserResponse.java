package io.github.fabiocintra.event_management.user.model.dto;

import io.github.fabiocintra.event_management.event.model.Event;
import io.github.fabiocintra.event_management.order.model.Order;
import io.github.fabiocintra.event_management.user.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public record UserResponse(
        String id,
        String name,
        String email,
        Instant createdAt,
        @Enumerated(EnumType.STRING)
        Set<Role> role,
        List<Order> orders,
        List<Event> events
) {
}
