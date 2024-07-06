package net.anyforum.backend.models.api.session;

public class SessionAPIResponse {
    private String message;
    private String token;

    public SessionAPIResponse(String message) {
        this.message = message;
    }

    public SessionAPIResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "SessionAPIResponse{" +
                "message='" + message + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
