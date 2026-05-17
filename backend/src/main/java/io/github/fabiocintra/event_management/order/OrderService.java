package io.github.fabiocintra.event_management.order;

import io.github.fabiocintra.event_management.order.model.Order;
import io.github.fabiocintra.event_management.order.model.OrderStatus;
import io.github.fabiocintra.event_management.utils.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public String createOrder(Order order){
        orderRepository.save(order);
        return order.getId();
    }

    public Order fecthOrder(String id){
        Optional<Order> orderFinded = orderRepository.findById(id);
        if(orderFinded.isEmpty()){
            throw new NotFoundException("Order not found!");
        }
        Order order = orderFinded.get();

        //busca os order_items
        //setar eles na order

        return order;
    }

    @Transactional
    public void payOrder(String orderId){
        Order order = fecthOrder(orderId);
        order.setStatus(OrderStatus.PAID);

        //mudo a quantidade dos itens da order
        //se item esgotou cancela a order
    }

    public void cancelOrder(String orderId) {
        Order order = fecthOrder(orderId);
        order.setStatus(OrderStatus.CANCELLED);
    }

    @Transactional
    public void restauredOder(String orderId) {
        Order order = fecthOrder(orderId);
        order.setStatus(OrderStatus.PENDING);

        //restaura os itens na order
    }
}
