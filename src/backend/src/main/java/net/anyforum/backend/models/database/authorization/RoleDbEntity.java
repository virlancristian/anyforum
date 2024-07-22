package net.anyforum.backend.models.database.authorization;

import com.sun.istack.NotNull;
import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleDbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleID")
    private Integer roleID;

    @Column(name = "role_name")
    @NotNull
    private String roleName;

    @Column(name = "role_color")
    @NotNull
    private String roleColor;

    public RoleDbEntity() {}

    public RoleDbEntity(String roleName, String roleColor) {
        this.roleName = roleName;
        this.roleColor = roleColor;
    }

    public RoleDbEntity(Integer roleID, String roleName, String roleColor) {
        this.roleID = roleID;
        this.roleName = roleName;
        this.roleColor = roleColor;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleColor() {
        return roleColor;
    }

    public void setRoleColor(String roleColor) {
        this.roleColor = roleColor;
    }

    @Override
    public String toString() {
        return "RoleDbEntity{" +
                "roleID=" + roleID +
                ", roleName='" + roleName + '\'' +
                ", roleColor='" + roleColor + '\'' +
                '}';
    }
}
