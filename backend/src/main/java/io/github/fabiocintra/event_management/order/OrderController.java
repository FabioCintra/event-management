package io.github.fabiocintra.event_management.order;

import io.github.fabiocintra.event_management.order.model.Order;
import io.github.fabiocintra.event_management.order.model.dto.OrderRequest;
import io.github.fabiocintra.event_management.order.model.dto.OrderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderRequest request){
        Order order = orderMapper.toEntity(request);
        String orderId = orderService.createOrder(order);
        OrderResponse orderResponse = new OrderResponse(orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @PutMapping
    public void orderPaiding(@RequestParam("id") String orderId){
        orderService.payOrder(orderId);
    }

    @PutMapping
    public void cancelOrder(@RequestParam("id") String orderId){
        orderService.cancelOrder(orderId);
    }

    @PutMapping
    public void restauredOrder(@RequestParam("id") String orderId){
        orderService.restauredOder(orderId);
    }

}
