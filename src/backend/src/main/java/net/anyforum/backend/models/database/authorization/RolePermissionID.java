package net.anyforum.backend.models.database.authorization;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class RolePermissionID implements Serializable {
    private Integer roleID;
    private Integer permissionID;

    public RolePermissionID(Integer roleID, Integer permissionID) {
        this.roleID = roleID;
        this.permissionID = permissionID;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public Integer getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(Integer permissionID) {
        this.permissionID = permissionID;
    }

    @Override
    public String toString() {
        return "RolePermissionID{" +
                "roleID=" + roleID +
                ", permissionID=" + permissionID +
                '}';
    }
}
