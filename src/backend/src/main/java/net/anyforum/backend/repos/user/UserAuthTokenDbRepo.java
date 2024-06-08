package net.anyforum.backend.repos.user;

import net.anyforum.backend.models.database.UserAuthTokenDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthTokenDbRepo extends JpaRepository<UserAuthTokenDbEntity, Integer> {
}
