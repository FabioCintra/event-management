package io.github.fabiocintra.event_management.ticket_type.model;

import io.github.fabiocintra.event_management.event.EventService;
import io.github.fabiocintra.event_management.event.model.Event;
import io.github.fabiocintra.event_management.ticket_type.TicketTypeService;
import io.github.fabiocintra.event_management.ticket_type.model.dto.TicketTypeRequest;
import io.github.fabiocintra.event_management.utils.annotations.Mapper;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class TicketTypeMapper {

    private final EventService eventService;

    public TicketType toEntity(TicketTypeRequest request){
        TicketType entity = new TicketType();
        Event event = eventService.findEventById(request.eventId());

        entity.setName(request.name());
        entity.setPrice(request.price());
        entity.setTotalQuantity(request.quantity());
        entity.setSaleStartAt(request.saleStartAt());
        entity.setSaleEndsAt(request.saleEndsAt());
        entity.setEvent(event);

        return entity;
    }

}
