package io.github.fabiocintra.event_management.order;

import io.github.fabiocintra.event_management.order.model.Order;
import io.github.fabiocintra.event_management.order.model.OrderStatus;
import io.github.fabiocintra.event_management.order.model.dto.OrderRequest;
import io.github.fabiocintra.event_management.user.UserService;
import io.github.fabiocintra.event_management.user.model.User;
import io.github.fabiocintra.event_management.utils.annotations.Mapper;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class OrderMapper {

    private final UserService userService;

    public Order toEntity(OrderRequest orderRequest){
        Order order = new Order();
        User attendee = userService.findById(orderRequest.attendeeId());

        order.setAttendee(attendee);
        order.setStatus(OrderStatus.PENDING);
        order.setTotalAmount(orderRequest.totalAmount());

        return order;
    }

}
