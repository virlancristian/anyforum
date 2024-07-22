package net.anyforum.backend.models.database.authorization;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_roles")
public class UserRoleDbEntity {
    @EmbeddedId
    private UserRoleID userRoleID;

    public UserRoleDbEntity() {}

    public UserRoleDbEntity(UserRoleID userRoleID) {
        this.userRoleID = userRoleID;
    }

    public UserRoleID getUserRoleID() {
        return userRoleID;
    }

    public void setUserRoleID(UserRoleID userRoleID) {
        this.userRoleID = userRoleID;
    }
}
