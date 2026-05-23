package io.github.fabiocintra.event_management.event.model.dto.event;

import io.github.fabiocintra.event_management.event.model.Location;
import io.github.fabiocintra.event_management.event.model.Status;
import io.github.fabiocintra.event_management.event.model.dto.location.LocationRequest;
import io.github.fabiocintra.event_management.ticket_type.model.TicketType;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public record EventResponse(
        String id,
        String title,
        String description,
        LocalDateTime startsAt,
        LocalDateTime endsAt,
        Location location,
        Instant createdAt,
        String organizerId,
        Status status,
        String organizerName,
        List<TicketType> ticketType
) {
}
