package net.anyforum.backend.repos.user;

import net.anyforum.backend.models.database.user.UserDataDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataDbRepo extends JpaRepository<UserDataDbEntity, String> {
    @Query(value = "SELECT * " +
            "FROM user_data " +
            "WHERE userID = :id",
            nativeQuery = true)
    UserDataDbEntity getUserByID(@Param("id") String id);

    @Query(value = "SELECT B.* " +
            "FROM users A " +
            "JOIN user_data B ON B.userid = A.id " +
            "WHERE A.username = :username",
            nativeQuery = true)
    UserDataDbEntity getUserByUsername(@Param("username") String username);
}
