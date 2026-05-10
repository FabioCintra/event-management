package io.github.fabiocintra.event_management.event;

import io.github.fabiocintra.event_management.event.model.Event;
import io.github.fabiocintra.event_management.event.model.Status;
import io.github.fabiocintra.event_management.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static io.github.fabiocintra.event_management.event.EventSpec.*;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final EventValidate eventValidate;

    public void createEvent(Event event) {
        eventValidate.eventIsValid(event);
        eventRepository.save(event);
    }

    public Event findEventById(String id){
        Optional<Event> eventOptional = eventRepository.findById(id);
        if(eventOptional.isEmpty()){
            throw new NotFoundException("Event not found!");
        }
        return eventOptional.get();
    }

    public Page<Event> customSearchEvents(
            String title,
            String city,
            String country,
            LocalDate startsAt,
            Status status,
            String organizerName,
            Integer page,
            Integer itemsPerPage
    ){
        Specification<Event> eventSpecification = Specification.where(
                ((root, query, cb) -> cb.conjunction())
        );

        if(title != null){
            eventSpecification = eventSpecification.and(titleLike(title));
        }
        if(city != null){
            eventSpecification = eventSpecification.and(cityLike(city));
        }
        if(country != null){
            eventSpecification = eventSpecification.and(countryLike(country));
        }
        if(startsAt != null){
            eventSpecification = eventSpecification.and(startsAtEqual(startsAt));
        }
        if(status != null){
            eventSpecification = eventSpecification.and(statusEqual(status));
        }
        if(organizerName != null){
            eventSpecification = eventSpecification.and(organizerNameLike(organizerName));
        }

        Pageable pageRequest = PageRequest.of(page, itemsPerPage);

        return eventRepository.findAll(eventSpecification, pageRequest);

    }

}
