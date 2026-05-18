package io.github.fabiocintra.event_management.ticket_type;

import io.github.fabiocintra.event_management.event.EventService;
import io.github.fabiocintra.event_management.event.model.Event;
import io.github.fabiocintra.event_management.ticket_type.model.TicketType;
import io.github.fabiocintra.event_management.utils.exceptions.TicketCannotRegisteredException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import static io.github.fabiocintra.event_management.ticket_type.TicketTypeSpec.*;

@Service
@RequiredArgsConstructor
public class TicketTypeService {

    private final TicketTypeRepository ticketTypeRepository;

    public void createTicketType(TicketType ticketType){
        if(ticketTypeRepository.existsByEvent(ticketType.getEvent())){
            throw new TicketCannotRegisteredException("Ticket Type already exists for this event!");
        }
        ticketTypeRepository.save(ticketType);
    }

    public TicketType findTicketTypesByEventId(Event event){
        TicketType ticketType = ticketTypeRepository.findByEvent(event);

        //trago seus order_items vou pensar
        return ticketType;
    }

    public Page<TicketType> findAllTicketTypes(String name, Double price, Integer page, Integer size) {
        Specification<TicketType> tickets = Specification.where(
                (root, query, cb) -> cb.conjunction()
        );

        if(name != null){
            tickets = tickets.and(nameLike(name));
        }
        if(price != null){
            tickets = tickets.and(priceBetween(price));
        }

        Pageable  pageRequest = PageRequest.of(page, size);
        return ticketTypeRepository.findAll(tickets,pageRequest);
    }
}
