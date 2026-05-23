package io.github.fabiocintra.event_management.order_item;

import io.github.fabiocintra.event_management.order.model.Order;
import io.github.fabiocintra.event_management.order_item.model.OrderItem;
import io.github.fabiocintra.event_management.ticket_type.TicketTypeService;
import io.github.fabiocintra.event_management.ticket_type.model.TicketType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository repository;
    private final TicketTypeService ticketTypeService;
    private final OrderItemValidate validate;

    @Transactional
    public void createOrderItem(OrderItem orderItem) {
        validate.orderItemIsValid(orderItem);
        repository.save(orderItem);

        TicketType ticketType = orderItem.getTicketType();
        ticketType.setSoldQuantity(orderItem.getQuantity() + ticketType.getSoldQuantity());
    }

    @Transactional
    public void orderItemCanceled(OrderItem orderItem) {
        repository.deleteById(orderItem.getId());
        TicketType ticketType = orderItem.getTicketType();
        ticketType.setSoldQuantity(ticketType.getSoldQuantity() - orderItem.getQuantity());
    }

    public List<OrderItem> findAllOrderItemsByOrder(Order order) {
        return repository.findByOrder(order);
    }
}
