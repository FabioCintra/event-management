package io.github.fabiocintra.event_management.ticket_type;

import io.github.fabiocintra.event_management.ticket_type.model.TicketType;
import io.github.fabiocintra.event_management.ticket_type.model.TicketTypeMapper;
import io.github.fabiocintra.event_management.ticket_type.model.dto.TicketTypeRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket/type")
@RequiredArgsConstructor
public class TicketTypeController {

    private final TicketTypeService service;
    private final TicketTypeMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTicketType(@RequestBody @Valid TicketTypeRequest request){
        TicketType ticketType = mapper.toEntity(request);
        service.createTicketType(ticketType);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public Page<TicketType> getAllTicketTypes(
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "price", required = false) Double price,
            @RequestParam(defaultValue = "0", value = "page", required = false) Integer page,
            @RequestParam(defaultValue = "10", value = "size", required = false)Integer size

    ){
        Page<TicketType> pageWithTickets = service.findAllTicketTypes(name, price, page, size);
        return pageWithTickets;
    }
}
