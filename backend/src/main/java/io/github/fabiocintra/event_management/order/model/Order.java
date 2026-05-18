package io.github.fabiocintra.event_management.order.model;

import io.github.fabiocintra.event_management.order_item.model.OrderItem;
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
    private BigDecimal totalAmount;

    @Column(name="status")
    private OrderStatus status;

    @CreationTimestamp
    @Column(name="created_at")
    private Instant createdAt;

    @Column(name="paid_at")
    private Instant paidAt;

    @ManyToOne
    @JoinColumn(name = "attendee_id")
    private User attendee;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItem;

}
