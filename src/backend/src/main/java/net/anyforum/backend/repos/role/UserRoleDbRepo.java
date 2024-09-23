package net.anyforum.backend.repos.role;

import net.anyforum.backend.models.authorization.UserRoleDbEntity;
import net.anyforum.backend.models.authorization.UserRoleID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleDbRepo extends JpaRepository<UserRoleDbEntity, UserRoleID> {
}
