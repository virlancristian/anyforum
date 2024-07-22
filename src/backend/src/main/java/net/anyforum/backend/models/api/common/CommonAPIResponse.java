package net.anyforum.backend.models.api.common;

public class CommonAPIResponse {
    private String message;
    private Object content;

    public CommonAPIResponse(String message) {
        this.message = message;
    }

    public CommonAPIResponse(String message, Object content) {
        this.message = message;
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
