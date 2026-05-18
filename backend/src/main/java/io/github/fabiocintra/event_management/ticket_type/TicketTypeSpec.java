package io.github.fabiocintra.event_management.ticket_type;

import io.github.fabiocintra.event_management.ticket_type.model.TicketType;
import org.springframework.data.jpa.domain.Specification;

public class TicketTypeSpec {

    public static Specification<TicketType> nameLike(String name) {
        return (root, query, cb) ->
            cb.like(cb.upper(root.get("name")), "%" + name.toUpperCase() + "%");
    }

    public static Specification<TicketType> priceBetween(Double price) {
        return (root, query, cb) ->
                cb.between(root.get("price"), 0.0, price);
    }

}
