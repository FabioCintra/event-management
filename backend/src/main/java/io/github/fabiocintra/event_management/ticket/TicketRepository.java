package io.github.fabiocintra.event_management.ticket;

import io.github.fabiocintra.event_management.order.model.Order;
import io.github.fabiocintra.event_management.ticket.model.Ticket;
import io.github.fabiocintra.event_management.ticket_type.model.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {
    List<Ticket> findByOrderAndTicketType(Order order, TicketType ticketType);
}
