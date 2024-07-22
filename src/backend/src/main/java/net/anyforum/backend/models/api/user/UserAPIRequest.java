package net.anyforum.backend.models.api.user;

public class UserAPIRequest {
    private String username;
    private String aboutMe;
    private String profilePictureURL;

    public UserAPIRequest() {}

    public UserAPIRequest(String username, String aboutMe, String profilePictureURL) {
        this.username = username;
        this.aboutMe = aboutMe;
        this.profilePictureURL = profilePictureURL;
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

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    @Override
    public String toString() {
        return "UserAPIRequest{" +
                "aboutMe='" + aboutMe + '\'' +
                ", profilePictureURL='" + profilePictureURL + '\'' +
                '}';
    }
}
