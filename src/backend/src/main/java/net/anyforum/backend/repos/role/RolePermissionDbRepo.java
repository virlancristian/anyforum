package net.anyforum.backend.repos.role;

import net.anyforum.backend.models.authorization.RolePermissionDbEntity;
import net.anyforum.backend.models.authorization.RolePermissionID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePermissionDbRepo extends JpaRepository<RolePermissionDbEntity, RolePermissionID> {
}
