package io.github.fabiocintra.event_management.order_item;

import io.github.fabiocintra.event_management.order.OrderService;
import io.github.fabiocintra.event_management.order.model.Order;
import io.github.fabiocintra.event_management.order_item.model.OrderItem;
import io.github.fabiocintra.event_management.order_item.model.dto.OrderItemRequest;
import io.github.fabiocintra.event_management.ticket_type.TicketTypeService;
import io.github.fabiocintra.event_management.ticket_type.model.TicketType;
import io.github.fabiocintra.event_management.utils.annotations.Mapper;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class OrderItemMapper {

    private final OrderService orderService;
    private final TicketTypeService ticketTypeService;

    public OrderItem toEntity(OrderItemRequest orderItemRequest) {
        OrderItem orderItem = new OrderItem();
        Order order = orderService.findOrderById(orderItemRequest.orderId());
        TicketType ticketType = ticketTypeService.findTicketTypeById(orderItemRequest.ticketTypeId());

        orderItem.setQuantity(orderItemRequest.quantity());
        orderItem.setUnitPrice(orderItemRequest.unitPrice());
        orderItem.setOrder(order);
        orderItem.setTicketType(ticketType);

        return orderItem;
    }

}
