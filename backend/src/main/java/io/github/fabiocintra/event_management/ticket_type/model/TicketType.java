package io.github.fabiocintra.event_management.ticket_type.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.fabiocintra.event_management.event.model.Event;
import io.github.fabiocintra.event_management.order_item.model.OrderItem;
import io.github.fabiocintra.event_management.ticket.model.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.List;

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

    @Column(name = "sale_starts_at")
    private Timestamp saleStartAt;

    @Column(name = "sale_ends_at")
    private Timestamp saleEndsAt;

    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonBackReference
    private Event event;

    @OneToMany(mappedBy = "ticketType")
    @JsonManagedReference
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "ticketType")
    @JsonManagedReference
    private List<Ticket>  tickets;

    public Integer quantityOfItemsAvailable(){
        return this.totalQuantity - this.soldQuantity;
    }
}
