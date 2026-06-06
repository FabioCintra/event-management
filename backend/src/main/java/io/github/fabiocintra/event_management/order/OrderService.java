package io.github.fabiocintra.event_management.order;

import io.github.fabiocintra.event_management.order.model.Order;
import io.github.fabiocintra.event_management.order.model.OrderStatus;
import io.github.fabiocintra.event_management.order_item.OrderItemService;
import io.github.fabiocintra.event_management.order_item.model.OrderItem;
import io.github.fabiocintra.event_management.ticket.TicketService;
import io.github.fabiocintra.event_management.user.UserService;
import io.github.fabiocintra.event_management.user.model.User;
import io.github.fabiocintra.event_management.utils.exceptions.MethodErrorException;
import io.github.fabiocintra.event_management.utils.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final TicketService ticketService;

    public String createOrder(Order order){
        orderRepository.save(order);
        return order.getId();
    }

    public Order findOrderById(String orderId){
        Optional<Order> orderFinded = orderRepository.findById(orderId);
        if(orderFinded.isEmpty()){
            throw new NotFoundException("Order not found!");
        }
        Order order = orderFinded.get();

        return fetchOrderItem(order);
    }

    public Page<Order> findOrderAllByAttendee(User  attendee){
        Specification<Order> specification = Specification.where(
                (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("attendee"), attendee)
        );
        Pageable  pageable = PageRequest.of(0, 10);
        Page<Order> orders = orderRepository.findAll(specification, pageable);
        return orders;
    }

    private Order fetchOrderItem(Order order){
        List<OrderItem> allOrderItemsByOrder = orderItemService.findAllOrderItemsByOrder(order);
        order.setOrderItem(allOrderItemsByOrder);
        return order;
    }

    @Transactional
    public void payOrder(String orderId){
        Order order = findOrderById(orderId);
        if(order.getStatus() !=  OrderStatus.PENDING){
            throw new MethodErrorException("Order status is not PENDING!");
        }
        order.setStatus(OrderStatus.PAID);
        order.setPaidAt(Instant.now());
        orderRepository.save(order);

        List<OrderItem> allOrderItemsByOrder = orderItemService.findAllOrderItemsByOrder(order);
        allOrderItemsByOrder.forEach(orderItem ->
                ticketService.createTicket(order,orderItem.getTicketType()));
    }

    @Transactional
    public void cancelOrder(String orderId) {
        Order order = findOrderById(orderId);
        if(order.getStatus() !=  OrderStatus.PENDING){
            throw new MethodErrorException("Order status is not PENDING!");
        }
        order.setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);

        List<OrderItem> orderItems = order.getOrderItem();
        orderItems.forEach(orderItemService::orderItemCanceled);
    }
}
