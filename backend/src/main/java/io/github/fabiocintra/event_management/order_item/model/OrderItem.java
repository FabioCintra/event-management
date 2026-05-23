package io.github.fabiocintra.event_management.order_item.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.fabiocintra.event_management.order.model.Order;
import io.github.fabiocintra.event_management.ticket_type.model.TicketType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="order_item_tb")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name="quantity")
    private int quantity;

    @Column(name="unit_price")
    private double unitPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ticket_type_id")
    @JsonBackReference
    private TicketType ticketType;

}
