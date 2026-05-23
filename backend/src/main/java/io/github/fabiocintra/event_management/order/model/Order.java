package io.github.fabiocintra.event_management.order.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.fabiocintra.event_management.order_item.model.OrderItem;
import io.github.fabiocintra.event_management.ticket.model.Ticket;
import io.github.fabiocintra.event_management.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name="order_tb")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name="total_amount")
    private Double totalAmount;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @CreationTimestamp
    @Column(name="create_at")
    private Instant createdAt;

    @Column(name="paid_at")
    private Instant paidAt;

    @ManyToOne
    @JoinColumn(name = "attendee_id")
    @JsonBackReference
    private User attendee;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private List<OrderItem> orderItem;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    private List<Ticket>  ticket;

}
