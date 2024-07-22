package net.anyforum.backend.repos.role;

import net.anyforum.backend.models.database.authorization.PermissionDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDbRepo extends JpaRepository<PermissionDbEntity, Integer> {
    @Query(value = "SELECT * " +
            "FROM permissions " +
            "WHERE permissionID = :id",
            nativeQuery = true)
    PermissionDbEntity getPermissionByID(@Param("id") Integer id);

    @Query(value = "SELECT * " +
            "FROM permissions " +
            "WHERE permission_category = :category",
            nativeQuery = true)
    List<PermissionDbEntity> getPermissionsByCategory(@Param("category") String category);

    @Query(value = "SELECT A.* " +
            "FROM permissions A " +
            "JOIN roles_permissions B ON B.permissionID = A.permissionID " +
            "JOIN roles C on C.roleID = B.roleID " +
            "WHERE C.roleID = :roleID",
            nativeQuery = true)
    List<PermissionDbEntity> getRolePermissions(@Param("roleID") Integer roleID);
}
