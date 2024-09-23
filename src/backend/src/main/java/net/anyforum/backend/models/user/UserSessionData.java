package net.anyforum.backend.models.user;

public class UserSessionData {
    private String id;
    private String username;
    private boolean isMuted;
    private boolean isBanned;
    private boolean isNSFWOn;

    public UserSessionData() {}

    public UserSessionData(String id, String username, boolean isMuted, boolean isBanned, boolean isNSFWOn) {
        this.id = id;
        this.username = username;
        this.isMuted = isMuted;
        this.isBanned = isBanned;
        this.isNSFWOn = isNSFWOn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean isNSFWOn() {
        return isNSFWOn;
    }

    public void setNSFWOn(boolean NSFWOn) {
        isNSFWOn = NSFWOn;
    }
}
