package net.anyforum.backend.constants;

//Possible message responses from the API
public enum MiddlewareMessage {
    INVALID_EMAIL("Invalid e-mail format."),
    INVALID_USERNAME("Invalid username format."),
    INVALID_PASSWORD("Invalid passsword format."),
    USERNAME_NOT_AVAILABLE("An account with the following username already exists."),
    EMAIL_NOT_AVAILABLE("An account with the following e-mail already exists."),
    MISSING_REQUEST_BODY_PARAMETERS("Missing request body parameter(s)."),
    OK("Valid request."),
    INVALID_USER_ID("Invalid user ID format."),
    USER_NOT_FOUND("User not found."),
    INVALID_SESSION_ID("Invalid session ID format."),
    SESSION_NOT_FOUND("Session not found."),
    INCORRECT_PASSWORD("Incorrect password.");

    private String message;

    private MiddlewareMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
