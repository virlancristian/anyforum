package net.anyforum.backend.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User was not found in the database.");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
