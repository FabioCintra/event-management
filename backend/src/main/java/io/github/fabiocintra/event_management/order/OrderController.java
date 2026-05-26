package io.github.fabiocintra.event_management.order;

import io.github.fabiocintra.event_management.order.model.Order;
import io.github.fabiocintra.event_management.order.model.dto.OrderRequest;
import io.github.fabiocintra.event_management.order.model.dto.OrderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody @Valid OrderRequest request){
        Order order = orderMapper.toEntity(request);
        String orderId = orderService.createOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderId);
    }

    @PutMapping("/payment")
    public void payOrder(@RequestParam("id") String orderId){
        orderService.payOrder(orderId);
    }

    @PutMapping("/cancel")
    public void cancelOrder(@RequestParam("id") String orderId){
        orderService.cancelOrder(orderId);
    }

//    @PutMapping("/restaured")
//    public void restauredOrder(@RequestParam("id") String orderId){
//        orderService.restauredOder(orderId);
//    }

}
