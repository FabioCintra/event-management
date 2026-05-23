package io.github.fabiocintra.event_management.order;

import io.github.fabiocintra.event_management.order.model.Order;
import io.github.fabiocintra.event_management.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByAttendee(User attendee);
}
