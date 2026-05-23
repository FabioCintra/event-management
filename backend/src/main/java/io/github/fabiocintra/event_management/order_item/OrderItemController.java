package io.github.fabiocintra.event_management.order_item;

import io.github.fabiocintra.event_management.order_item.model.OrderItem;
import io.github.fabiocintra.event_management.order_item.model.dto.OrderItemRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-item")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService service;
    private final OrderItemMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrderItem(@RequestBody @Valid OrderItemRequest request) {
        OrderItem orderItem = mapper.toEntity(request);
        service.createOrderItem(orderItem);
    }

}
