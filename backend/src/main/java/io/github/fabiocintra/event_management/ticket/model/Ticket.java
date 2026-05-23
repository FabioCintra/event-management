package io.github.fabiocintra.event_management.ticket.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.github.fabiocintra.event_management.order.model.Order;
import io.github.fabiocintra.event_management.ticket_type.model.TicketType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;

@Entity
@Table(name = "ticket_tb")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "qr_code")
    private String qrCode;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Column(name = "issued_at")
    @CreationTimestamp
    private Timestamp issueAt;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "ticket_type_id")
    @JsonBackReference
    private TicketType ticketType;
}
