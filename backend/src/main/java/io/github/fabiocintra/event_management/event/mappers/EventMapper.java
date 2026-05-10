package io.github.fabiocintra.event_management.event.mappers;


import io.github.fabiocintra.event_management.event.model.Event;
import io.github.fabiocintra.event_management.event.model.Location;
import io.github.fabiocintra.event_management.event.model.dto.event.EventRequest;
import io.github.fabiocintra.event_management.event.model.dto.event.EventResponse;
import io.github.fabiocintra.event_management.user.UserService;
import io.github.fabiocintra.event_management.user.model.User;
import io.github.fabiocintra.event_management.utils.annotations.Mapper;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class EventMapper {

    private final UserService userService;
    private final LocationMapper locationMapper;

    public Event toEntity(EventRequest eventRequest) {
        Event event = new Event();
        User organizer = userService.findById(eventRequest.organizerId());
        Location location = locationMapper.toEntity(eventRequest.location());

        event.setTitle(eventRequest.title());
        event.setDescription(eventRequest.description());
        event.setStartsAt(eventRequest.startsAt());
        event.setEndsAt(eventRequest.endsAt());
        event.setLocation(location);
        event.setOrganizerId(organizer);

        return event;
    }

    public EventResponse toResponse(Event event) {
        User organizer = event.getOrganizerId();
        return new EventResponse(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getStartsAt(),
                event.getEndsAt(),
                event.getLocation(),
                event.getCreatedAt(),
                organizer.getId(),
                organizer.getName()
        );
    }

}
