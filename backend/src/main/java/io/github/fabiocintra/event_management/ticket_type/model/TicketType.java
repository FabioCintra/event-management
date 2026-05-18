package io.github.fabiocintra.event_management.ticket_type.model;

import io.github.fabiocintra.event_management.event.model.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Entity
@Table(name = "ticket_type_tb")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "total_quantity")
    private int totalQuantity;

    @Column(name = "sold_quantity")
    private int soldQuantity;

    @Column(name = "sales_start_at")
    private Timestamp saleStartAt;

    @Column(name = "sale_ends_at")
    private Timestamp saleEndsAt;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
