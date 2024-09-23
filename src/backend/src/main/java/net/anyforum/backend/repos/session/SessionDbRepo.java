package net.anyforum.backend.repos.session;

import net.anyforum.backend.models.session.SessionDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionDbRepo extends JpaRepository<SessionDbEntity, String> {
    @Query(value = "SELECT * " +
            "FROM user_sessions " +
            "WHERE sessionid = :id", nativeQuery = true)
    SessionDbEntity getSessionByID(@Param("id") String sessionID);

    @Query(value = "SELECT * " +
            "FROM user_sessions " +
            "WHERE userID = :id", nativeQuery = true)
    List<SessionDbEntity> getSessionByUser(@Param("id") String userID);

    @Query(value = "SELECT * " +
            "FROM user_sessions " +
            "WHERE session_token = :token",
            nativeQuery = true)
    SessionDbEntity getSessionByToken(@Param("token") String token);
}
