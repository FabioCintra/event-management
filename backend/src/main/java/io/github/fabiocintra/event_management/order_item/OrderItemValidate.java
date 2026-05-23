package io.github.fabiocintra.event_management.order_item;

import io.github.fabiocintra.event_management.order.model.Order;
import io.github.fabiocintra.event_management.order.model.OrderStatus;
import io.github.fabiocintra.event_management.order_item.model.OrderItem;
import io.github.fabiocintra.event_management.ticket_type.model.TicketType;
import io.github.fabiocintra.event_management.utils.annotations.Validate;
import io.github.fabiocintra.event_management.utils.exceptions.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Validate
public class OrderItemValidate {
    public void orderItemIsValid(OrderItem orderItem){
        TicketType ticketType = orderItem.getTicketType();
        Order order = orderItem.getOrder();
        if(order.getStatus() != OrderStatus.PENDING){
            throw new OrderInvalidException("Order has been pending");
        }
        if(ticketType.quantityOfItemsAvailable() < orderItem.getQuantity()){
            throw new OrderItemException("This quantity of tickets is greater than the available!");
        }
        if(ticketType.getPrice() != orderItem.getUnitPrice()){
            throw new OrderItemException("The unit price is different the original price!");
        }
        LocalDateTime start = ticketType.getSaleStartAt().toLocalDateTime();
        LocalDateTime end = ticketType.getSaleEndsAt().toLocalDateTime();
        if(start.isAfter(LocalDateTime.now()) || end.isBefore(LocalDateTime.now())){
            throw new OrderItemException("The ticket has buy between " + start + " and " + end);
        }
    }
}
