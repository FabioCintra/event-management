package io.github.fabiocintra.event_management.event;

import io.github.fabiocintra.event_management.event.model.Event;
import io.github.fabiocintra.event_management.user.UserRepository;
import io.github.fabiocintra.event_management.user.model.User;
import io.github.fabiocintra.event_management.utils.annotations.Validate;
import io.github.fabiocintra.event_management.utils.exceptions.EventRegisteredException;
import io.github.fabiocintra.event_management.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Validate
@RequiredArgsConstructor
public class EventValidate {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public void eventIsValid(Event event) {
        User organizer = event.getOrganizerId();
        String title = event.getTitle();


        if(!existsOrganizer(organizer.getId())){
            throw new NotFoundException("Organizer not found!");
        }

        //checa por precaucao se o organizador foi passado o do evento mesmo!
        if(event.getId() != null && !isOrganizer(organizer,event.getId())){
            throw new EventRegisteredException("This not organizer thats event!");
        }

        if(eventRepository.existsByOrganizerIdAndTitle(organizer, title) && !isSameProject(event)){
            throw new EventRegisteredException("There is already an event with this title by this organizer!");
        }


    }

    private Boolean existsOrganizer(String organizerId){
        Optional<User> organizer = userRepository.findById(organizerId);
        return organizer.isPresent();
    }

    private Boolean isOrganizer(User organizer, String eventId){
        Event event = eventRepository.findById(eventId).get();
        return organizer.getId().equals(event.getOrganizerId().getId());
    }

    private Boolean isSameProject(Event event){
        String idEventFinded = eventRepository.findByOrganizerIdAndTitle(event.getOrganizerId(), event.getTitle()).getId();
        return event.getId().equals(idEventFinded);
    }

}
