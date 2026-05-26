package io.github.fabiocintra.event_management.user.model;

public enum Role {
    ORGANIZER,
    ATTENDEE,
    STAFF;

    public static String mapToString(Role role) {
        if (role == Role.ORGANIZER) {
            return "ORGANIZER";
        }
        if (role == Role.STAFF) {
            return "STAFF";
        }
        return "ATTENDEE";
    }
}
