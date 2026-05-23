package io.github.fabiocintra.event_management.ticket;

import io.github.fabiocintra.event_management.ticket.model.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService service;

    @GetMapping
    public ResponseEntity<List<Ticket>> findTicket(
            @RequestParam("order_id") String orderId, @RequestParam("ticket_type_id") String ticketTypeId) {

        List<Ticket> ticket = service.findTicket(orderId, ticketTypeId);
        return ResponseEntity.status(HttpStatus.FOUND).body(ticket);

    }

}
