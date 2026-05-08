package io.github.fabiocintra.event_management.user.model.dto;

import io.github.fabiocintra.event_management.user.model.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponse(
        String id,
        String name,
        String email,
        LocalDateTime createdAt,
        @Enumerated(EnumType.STRING)
        Role role
) {
}
