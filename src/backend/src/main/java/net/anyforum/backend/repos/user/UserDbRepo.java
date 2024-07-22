package net.anyforum.backend.repos.user;

import net.anyforum.backend.models.database.user.UserDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDbRepo extends JpaRepository<UserDbEntity, Integer> {
    @Query(value = "SELECT * " +
                    "FROM users " +
                    "WHERE id = :id", nativeQuery = true)
    UserDbEntity getUserById(@Param("id") String id);

    @Query(value = "SELECT * " +
            "FROM users " +
            "WHERE username = :username", nativeQuery = true)
    UserDbEntity getUserByUsername(@Param("username") String username);

    @Query(value = "SELECT *" +
            "FROM users " +
            "WHERE email = :email", nativeQuery = true)
    UserDbEntity getUserByEmail(@Param("email") String email);
}
