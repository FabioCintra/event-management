package io.github.fabiocintra.event_management.ticket_type;

import io.github.fabiocintra.event_management.event.model.Event;
import io.github.fabiocintra.event_management.ticket_type.model.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketTypeRepository extends JpaRepository<TicketType, String>, JpaSpecificationExecutor<TicketType> {
    Boolean existsByEvent(Event event);
    TicketType findByEvent(Event event);
}
