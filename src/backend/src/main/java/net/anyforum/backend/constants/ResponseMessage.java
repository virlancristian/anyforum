package net.anyforum.backend.constants;

//Possible message responses from the API
public enum ResponseMessage {
    INVALID_EMAIL("Invalid e-mail format."),
    INVALID_USERNAME("Invalid username format."),
    INVALID_PASSWORD("Invalid passsword format."),
    USERNAME_NOT_AVAILABLE("An account with the following username already exists."),
    EMAIL_NOT_AVAILABLE("An account with the following e-mail already exists."),
    MISSING_REQUEST_BODY_PARAMETERS("Missing request body parameter(s)."),
    OK("Valid request.");

    private String message;

    private ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
