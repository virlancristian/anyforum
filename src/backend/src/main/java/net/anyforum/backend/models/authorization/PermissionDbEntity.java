package net.anyforum.backend.models.authorization;

import com.sun.istack.NotNull;
import jakarta.persistence.*;

@Entity
@Table(name = "permissions")
public class PermissionDbEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permissionID")
    private Integer permissionID;

    @Column(name = "permission_category")
    @NotNull
    private String category;

    @Column(name = "permission_action")
    @NotNull
    private String action;

    public PermissionDbEntity() {}

    public PermissionDbEntity(String category, String action) {
        this.category = category;
        this.action = action;
    }

    public PermissionDbEntity(Integer permissionID, String category, String action) {
        this.permissionID = permissionID;
        this.category = category;
        this.action = action;
    }

    public Integer getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(Integer permissionID) {
        this.permissionID = permissionID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "PermissionDbEntity{" +
                "permissionID=" + permissionID +
                ", category='" + category + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
