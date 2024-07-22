package net.anyforum.backend.models.database.user;

import jakarta.persistence.*;

@Entity
@Table(name = "user_data")
public class UserDataDbEntity {
    @Id
    private String userID;

    @Lob
    @Column(name = "about_me", length = 1000)
    private String aboutMe;

    @Column(name = "profile_picture_url", length = 500)
    private String profilePictureURL;

    @Column(name = "joined_at")
    private String joinedAt;

    public UserDataDbEntity() {}

    public UserDataDbEntity(String aboutMe, String profilePictureURL, String joinedAt) {
        this.aboutMe = aboutMe;
        this.profilePictureURL = profilePictureURL;
        this.joinedAt = joinedAt;
    }

    public UserDataDbEntity(String userID, String aboutMe, String profilePictureURL, String joinedAt) {
        this.userID = userID;
        this.aboutMe = aboutMe;
        this.profilePictureURL = profilePictureURL;
        this.joinedAt = joinedAt;
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

    @Override
    public String toString() {
        return "UserDataDbEntity{" +
                "userID='" + userID + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                ", profilePictureURL='" + profilePictureURL + '\'' +
                ", joinedAt='" + joinedAt + '\'' +
                '}';
    }
}
