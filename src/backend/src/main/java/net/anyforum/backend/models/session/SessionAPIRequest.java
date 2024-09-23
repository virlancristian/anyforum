package net.anyforum.backend.models.session;

public class SessionAPIRequest {
    //both are optional depending on the request but at least 1 must be provided
    // (userID is used for creating a session, sessionID is used for getting an existing session)
    private String userID;
    private String sessionID;

    public SessionAPIRequest() {}

    public SessionAPIRequest(String userID, String sessionID) {
        this.userID = userID;
        this.sessionID = sessionID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    @Override
    public String toString() {
        return "SessionAPIRequest{" +
                "userID='" + userID + '\'' +
                ", sessionID='" + sessionID + '\'' +
                '}';
    }
}
