package io.github.fabiocintra.event_management.event.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Location(
        @Column(name = "country")
        String country,
        @Column(name = "city")
        String city,
        @Column(name = "street")
        String street,
        @Column(name = "complement")
        String complement,
        @Column(name = "number_location")
        Integer number
){}
