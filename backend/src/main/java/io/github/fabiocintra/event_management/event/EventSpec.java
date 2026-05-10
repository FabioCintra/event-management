package io.github.fabiocintra.event_management.event;

import io.github.fabiocintra.event_management.event.model.Event;
import io.github.fabiocintra.event_management.event.model.Status;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class EventSpec {

    public static Specification<Event> titleLike(String title) {
        return (
                (root, query, cb) ->
                cb.like(cb.upper(root.get("title")), "%" + title.toUpperCase() + "%")
        );
    }

    public static Specification<Event> cityLike(String city) {
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("location").get("city")),"%" + city.toUpperCase() + "%");
    }

    public static Specification<Event> countryLike(String country) {
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("location").get("country")),"%" + country.toUpperCase() + "%");
    }

    public static Specification<Event> startsAtEqual(LocalDate startsAt) {
        return (root, query, cb) ->
                cb.equal(
                        cb.function("DATE", LocalDate.class,root.get("startsAt")),
                        startsAt
                );
    }

    public static Specification<Event> statusEqual(Status status){
        return (root, query, cb) ->
                cb.equal(root.get("status"), status);
    }

    public static Specification<Event> organizerNameLike(String organizerName) {
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("organizerId").get("name")),"%" + organizerName.toUpperCase() + "%");
    }

}
