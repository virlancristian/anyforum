package net.anyforum.backend.repos.role;

import net.anyforum.backend.models.authorization.RoleDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDbRepo extends JpaRepository<RoleDbEntity, Integer> {
    @Query(value = "SELECT * " +
            "FROM roles " +
            "WHERE roleID = :id",
            nativeQuery = true)
    RoleDbEntity getRoleByID(@Param("id") Integer id);

    @Query(value = "SELECT * " +
            "FROM roles " +
            "WHERE role_name = :name",
            nativeQuery = true)
    RoleDbEntity getRoleByName(@Param("name") String name);

    @Query(value = "SELECT A.* " +
            "FROM roles A " +
            "JOIN users_roles B ON A.roleID = B.roleID " +
            "JOIN users C ON B.userID = C.id " +
            "WHERE C.id = :id",
            nativeQuery = true)
    List<RoleDbEntity> getUserRolesByID(@Param("id") String userID);

    @Query(value = "SELECT A.* " +
            "FROM roles A " +
            "JOIN users_roles B on B.roleID = A.roleID " +
            "JOIN user_data C on C.userid = B.userID " +
            "WHERE C.username = :username",
            nativeQuery = true)
    List<RoleDbEntity> getUserRolesByUsername(@Param("username") String username);
}
