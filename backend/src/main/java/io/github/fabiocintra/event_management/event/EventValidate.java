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

        if(eventRepository.existsByOrganizerIdAndTitle(organizer, title)){
            throw new EventRegisteredException("There is already an event with this title by this organizer!");
        }


    }

    private Boolean existsOrganizer(String organizerId){
        Optional<User> organizer = userRepository.findById(organizerId);
        return organizer.isPresent();
    }

}
