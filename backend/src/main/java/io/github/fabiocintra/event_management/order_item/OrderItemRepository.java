package io.github.fabiocintra.event_management.order_item;

import io.github.fabiocintra.event_management.order.model.Order;
import io.github.fabiocintra.event_management.order_item.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {

    List<OrderItem> findByOrder(Order order);

}
