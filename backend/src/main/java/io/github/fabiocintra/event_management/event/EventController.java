package io.github.fabiocintra.event_management.event;

import io.github.fabiocintra.event_management.event.mappers.EventMapper;
import io.github.fabiocintra.event_management.event.model.Event;
import io.github.fabiocintra.event_management.event.model.Status;
import io.github.fabiocintra.event_management.event.model.dto.event.EventRequest;
import io.github.fabiocintra.event_management.event.model.dto.event.EventResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PreAuthorize("hasRole('ORGANIZER')")
    public String createdEvent(@RequestBody @Valid EventRequest request, Authentication auth) {
        Event event = eventMapper.toEntity(request);
        String eventId = eventService.createEvent(event,auth);

        return eventId;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EventResponse findEventById(@PathVariable("id") String id){
        Event eventFinded = eventService.findEventById(id);
        return eventMapper.toResponse(eventFinded);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<EventResponse> searchCustomEvents(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "country", required = false) String country,
            @RequestParam(value = "date_starts_at",required = false) LocalDate startsAt,
            @RequestParam(value = "status",  required = false) Status status,
            @RequestParam(value = "organizer_name",required = false) String organizerName,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "items_per_page", defaultValue = "10") Integer itemsPerPage
    ){
        Page<Event> eventsFinded = eventService.customSearchEvents(
                title,
                city,
                country,
                startsAt,
                status,
                organizerName,
                page,
                itemsPerPage
        );

        Page<EventResponse> eventResponsesPage = eventsFinded.map(eventMapper::toResponse);

        return  eventResponsesPage;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ORGANIZER')")
    public void updateEvent(@RequestBody @Valid EventRequest request, @PathVariable("id") String id, Authentication auth) {
        Event event = eventMapper.toEntity(request);
        event.setId(id);
        eventService.updateEvent(event, auth);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ORGANIZER')")
    public void deleteEvent(@RequestParam("eventId") String id,Authentication auth){
        eventService.deleteEvent(auth,id);
    }

}
