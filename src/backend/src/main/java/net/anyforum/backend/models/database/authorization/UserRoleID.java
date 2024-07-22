package net.anyforum.backend.models.database.authorization;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class UserRoleID implements Serializable {
    private String userID;
    private Integer roleID;

    public UserRoleID() {}

    public UserRoleID(String userID, Integer roleID) {
        this.userID = userID;
        this.roleID = roleID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    @Override
    public String toString() {
        return "UserRoleID{" +
                "userID='" + userID + '\'' +
                ", roleID=" + roleID +
                '}';
    }
}
