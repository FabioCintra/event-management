package io.github.fabiocintra.event_management.event;

import io.github.fabiocintra.event_management.event.model.Event;
import io.github.fabiocintra.event_management.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, String>, JpaSpecificationExecutor<Event> {

    Boolean existsByOrganizerIdAndTitle(User organazerId, String title);
    Event findByOrganizerIdAndTitle(User organizerId, String title);
    List<Event> findAllByOrganizerId(User organizer);
}
