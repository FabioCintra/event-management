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
    public void createdEvent(@RequestBody @Valid EventRequest request){
        Event event = eventMapper.toEntity(request);
        eventService.createEvent(event);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public EventResponse findEventById(@PathVariable("id") String id){
        Event eventFinded = eventService.findEventById(id);
        return eventMapper.toResponse(eventFinded);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
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
    public void updateEvent(@RequestBody @Valid EventRequest request, @PathVariable("id") String id){
        Event event = eventMapper.toEntity(request);
        event.setId(id);
        eventService.updateEvent(event);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable("id") String id){
        eventService.deleteEvent(id);
    }

}
