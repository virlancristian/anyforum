package net.anyforum.backend.repos.session;

import net.anyforum.backend.models.database.SessionDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionDbRepo extends JpaRepository<SessionDbEntity, String> {
    @Query(value = "SELECT * " +
            "FROM user_sessions " +
            "WHERE sessionid = :id", nativeQuery = true)
    SessionDbEntity getSessionByID(@Param("id") String sessionID);

    @Query(value = "SELECT * " +
            "FROM user_sessions " +
            "WHERE userID = :id", nativeQuery = true)
    SessionDbEntity getSessionByUser(@Param("id") String userID);
}
