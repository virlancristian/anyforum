package net.anyforum.backend.models.api.user;

public class UserDataAPIResponse {
    private String username;
    private String userID;
    private String aboutMe;
    private String profilePictureURL;
    private String joinedAt;
    private boolean isMuted;
    private boolean isBanned;
    private boolean nsfwOn;

    public UserDataAPIResponse() {
        this.username = "";
        this.userID = "";
        this.aboutMe = "";
        this.profilePictureURL = "";
        this.joinedAt = "";
        this.isBanned = false;
        this.isMuted = false;
        this.nsfwOn = false;
    }

    public UserDataAPIResponse(String username, String userID, String aboutMe, String profilePictureURL, String joinedAt, boolean isMuted, boolean isBanned, boolean nsfwOn) {
        this.username = username;
        this.userID = userID;
        this.aboutMe = aboutMe;
        this.profilePictureURL = profilePictureURL;
        this.joinedAt = joinedAt;
        this.isMuted = isMuted;
        this.isBanned = isBanned;
        this.nsfwOn = nsfwOn;
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

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
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
        return nsfwOn;
    }

    public void setNsfwOn(boolean nsfwOn) {
        this.nsfwOn = nsfwOn;
    }
}
