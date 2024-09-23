package net.anyforum.backend.models.api.authorization;

import net.anyforum.backend.models.authorization.PermissionDbEntity;
import net.anyforum.backend.models.authorization.RoleDbEntity;

import java.util.LinkedList;
import java.util.List;

public class AuthorizationAPIResponse {
    private String message;
    private List<PermissionDbEntity> permissions;
    private List<RoleDbEntity> roles;

    public AuthorizationAPIResponse() {}

    public AuthorizationAPIResponse(String message) {
        this.message = message;
        this.permissions = new LinkedList<>();
        this.roles = new LinkedList<>();
    }

    public AuthorizationAPIResponse(String message, List<PermissionDbEntity> permissions) {
        this.message = message;
        this.permissions = permissions;
        this.roles = new LinkedList<>();
    }

    public AuthorizationAPIResponse(String message, List<PermissionDbEntity> permissions, List<RoleDbEntity> roles) {
        this.message = message;
        this.permissions = permissions;
        this.roles = roles;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PermissionDbEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDbEntity> permissions) {
        this.permissions = permissions;
    }

    public List<RoleDbEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDbEntity> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "AuthorizationAPIResponse{" +
                "message='" + message + '\'' +
                ", permissions=" + permissions +
                ", roles=" + roles +
                '}';
    }
}
