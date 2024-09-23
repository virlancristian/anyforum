package net.anyforum.backend.models.user;

import com.sun.istack.NotNull;
import jakarta.persistence.*;

@Entity
@Table(name = "user_data")
public class UserDataDbEntity {
    @Id
    private String userID;

    @Column(name = "username")
    @NotNull
    private String username;

    @Lob
    @Column(name = "about_me", length = 1000)
    private String aboutMe;

    @Column(name = "joined_at")
    private String joinedAt;

    public UserDataDbEntity() {}

    public UserDataDbEntity(String username, String aboutMe, String joinedAt) {
        this.username = username;
        this.aboutMe = aboutMe;
        this.joinedAt = joinedAt;
    }

    public UserDataDbEntity(String userID, String username, String aboutMe, String joinedAt) {
        this.userID = userID;
        this.username = username;
        this.aboutMe = aboutMe;
        this.joinedAt = joinedAt;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    @Override
    public String toString() {
        return "UserDataDbEntity{" +
                "userID='" + userID + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                ", joinedAt='" + joinedAt + '\'' +
                '}';
    }
}
