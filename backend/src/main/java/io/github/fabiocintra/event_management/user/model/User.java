package io.github.fabiocintra.event_management.user.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.github.fabiocintra.event_management.event.model.Event;
import io.github.fabiocintra.event_management.order.model.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name="user_tb")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "create_at")
    @CreationTimestamp
    private Instant createdAt;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "enum_tb",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Set<Role> role = new HashSet<>(Set.of(Role.ATTENDEE));

    @OneToMany(mappedBy = "organizerId")
    @JsonManagedReference
    private List<Event> events;

    @OneToMany(mappedBy = "attendee")
    @JsonManagedReference
    private List<Order> orders;
}
