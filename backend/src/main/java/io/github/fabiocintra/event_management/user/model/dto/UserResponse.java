package io.github.fabiocintra.event_management.user.model.dto;

import io.github.fabiocintra.event_management.user.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.Instant;
import java.util.Set;

public record UserResponse(
        String id,
        String name,
        String email,
        Instant createdAt,
        @Enumerated(EnumType.STRING)
        Set<Role> role
) {
}
