package io.github.fabiocintra.event_management.utils.exceptions;

public class TicketCannotRegisteredException extends RuntimeException {
    public TicketCannotRegisteredException(String message) {
        super(message);
    }
}
