package io.github.fabiocintra.event_management.event;

import io.github.fabiocintra.event_management.event.model.Event;
import io.github.fabiocintra.event_management.user.UserRepository;
import io.github.fabiocintra.event_management.user.model.Role;
import io.github.fabiocintra.event_management.user.model.User;
import io.github.fabiocintra.event_management.utils.annotations.Validate;
import io.github.fabiocintra.event_management.utils.exceptions.EventException;
import io.github.fabiocintra.event_management.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Optional;

@Validate
@RequiredArgsConstructor
public class EventValidate {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public void eventIsValid(Event event, Authentication auth) {
        User organizer = event.getOrganizer();
        String title = event.getTitle();
        User user = (User) auth.getPrincipal();

        if (!organizer.getRole().contains(Role.ORGANIZER)){
            throw new EventException("Cannot register an event with an organizer!");
        }

        if(!existsOrganizer(organizer.getId())){
            throw new NotFoundException("Organizer not found!");
        }

        if(!organizer.getId().equals(user.getId())){
            throw  new EventException("Cannot register an event with another organizer!");
        }

        //checa por precaucao se o organizador foi passado o do evento mesmo!
        if(event.getId() != null && !isOrganizer(organizer,event.getId())){
            throw new EventException("This not organizer thats event!");
        }

        if(eventRepository.existsByOrganizerAndTitle(organizer, title) && !isSameProject(event)){
            throw new EventException("There is already an event with this title by this organizer!");
        }


    }

    private Boolean existsOrganizer(String organizerId){
        Optional<User> organizer = userRepository.findById(organizerId);
        return organizer.isPresent();
    }

    private Boolean isOrganizer(User organizer, String eventId){
        Event event = eventRepository.findById(eventId).get();
        return organizer.getId().equals(event.getOrganizer().getId());
    }

    private Boolean isSameProject(Event event){
        String idEventFinded = eventRepository.findByOrganizerAndTitle(event.getOrganizer(), event.getTitle()).getId();
        return event.getId().equals(idEventFinded);
    }

}
