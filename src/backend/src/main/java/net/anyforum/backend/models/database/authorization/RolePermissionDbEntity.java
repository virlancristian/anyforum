package net.anyforum.backend.models.database.authorization;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "roles_permissions")
public class RolePermissionDbEntity {
    @EmbeddedId
    private RolePermissionID id;

    public RolePermissionDbEntity(RolePermissionID id) {
        this.id = id;
    }

    public RolePermissionID getId() {
        return id;
    }

    public void setId(RolePermissionID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RolePermissionDbEntity{" +
                "id=" + id +
                '}';
    }
}
