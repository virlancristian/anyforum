package net.anyforum.backend.models.api.session;

public class SessionJwtToken {
    private String username;
    private String userID;
    private String email;
    private boolean isMuted;
    private boolean isBanned;
    private boolean nsfwOn;
    private String sessionID;
    private String sessionToken;
    private String expiresAt;

    public SessionJwtToken(String username, String userID, String email, boolean isMuted, boolean isBanned, boolean nsfwOn, String sessionID, String sessionToken, String expiresAt) {
        this.username = username;
        this.userID = userID;
        this.email = email;
        this.isMuted = isMuted;
        this.isBanned = isBanned;
        this.nsfwOn = nsfwOn;
        this.sessionID = sessionID;
        this.sessionToken = sessionToken;
        this.expiresAt = expiresAt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void setMuted(boolean muted) {
        isMuted = muted;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public boolean isNsfwOn() {
        return nsfwOn;
    }

    public void setNsfwOn(boolean nsfwOn) {
        this.nsfwOn = nsfwOn;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(String expiresAt) {
        this.expiresAt = expiresAt;
    }

    @Override
    public String toString() {
        return "{" +
                "\"username\":\"" + username + "\"," +
                "\"userID\":\"" + userID + "\"," +
                "\"email\":\"" + email + "\"," +
                "\"isMuted\":" + isMuted + "," +
                "\"isBanned\":" + isBanned + "," +
                "\"nsfwOn\":" + nsfwOn + "," +
                "\"sessionID\":\"" + sessionID + "\"," +
                "\"sessionToken\":\"" + sessionToken + "\"," +
                "\"expiresAt\": \"" + expiresAt + "\"" +
                "}";
    }
}
