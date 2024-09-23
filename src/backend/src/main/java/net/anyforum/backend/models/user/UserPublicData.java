package net.anyforum.backend.models.user;

public class UserPublicData {
    private String id;
    private String username;
    private String aboutMe;
    private String joinedAt;
    private boolean isMuted;
    private boolean isBanned;
    private boolean isNsfwOn;

    public UserPublicData() {}

    public UserPublicData(String id, String username, String aboutMe, String joinedAt, boolean isMuted, boolean isBanned, boolean isNsfwOn) {
        this.id = id;
        this.username = username;
        this.aboutMe = aboutMe;
        this.joinedAt = joinedAt;
        this.isMuted = isMuted;
        this.isBanned = isBanned;
        this.isNsfwOn = isNsfwOn;
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

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(String joinedAt) {
        this.joinedAt = joinedAt;
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
        return isNsfwOn;
    }

    public void setNsfwOn(boolean nsfwOn) {
        isNsfwOn = nsfwOn;
    }
}
