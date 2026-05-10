package io.github.fabiocintra.event_management.event.model.dto.event;

import io.github.fabiocintra.event_management.event.model.Location;
import io.github.fabiocintra.event_management.event.model.dto.location.LocationRequest;

import java.time.Instant;
import java.time.LocalDateTime;

public record EventResponse(
        String id,
        String title,
        String description,
        LocalDateTime startsAt,
        LocalDateTime endsAt,
        Location location,
        Instant createdAt,
        String organizerId,
        String organizerName
) {
}
