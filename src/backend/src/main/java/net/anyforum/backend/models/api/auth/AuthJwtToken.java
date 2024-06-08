package net.anyforum.backend.models.api.auth;

public class AuthJwtToken {
    private String userID;
    private String token;
    private boolean isBanned;
    private boolean isMuted;
    private boolean nsfwOn;

    public AuthJwtToken(String userID, String token) {
        this.userID = userID;
        this.token = token;
        this.isBanned = true;
        this.isMuted = true;
        this.nsfwOn = false;
    }

    public AuthJwtToken(String userID, String token, boolean isBanned, boolean isMuted, boolean nsfwOn) {
        this.userID = userID;
        this.token = token;
        this.isBanned = isBanned;
        this.isMuted = isMuted;
        this.nsfwOn = nsfwOn;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public void setMuted(boolean muted) {
        isMuted = muted;
    }

    public boolean isNsfwOn() {
        return nsfwOn;
    }

    public void setNsfwOn(boolean nsfwOn) {
        this.nsfwOn = nsfwOn;
    }

    @Override
    public String toString() {
        return "AuthJwtToken{" +
                "userID='" + userID + '\'' +
                ", token='" + token + '\'' +
                ", isBanned=" + isBanned +
                ", isMuted=" + isMuted +
                ", nsfwOn=" + nsfwOn +
                '}';
    }
}
