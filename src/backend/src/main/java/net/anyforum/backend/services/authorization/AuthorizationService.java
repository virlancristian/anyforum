package net.anyforum.backend.services.authorization;

import net.anyforum.backend.models.database.authorization.PermissionDbEntity;
import net.anyforum.backend.models.database.authorization.RoleDbEntity;
import net.anyforum.backend.repos.role.PermissionDbRepo;
import net.anyforum.backend.repos.role.RoleDbRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class AuthorizationService {
    @Autowired
    private RoleDbRepo roleDbRepo;
    @Autowired
    private PermissionDbRepo permissionDbRepo;
    private static final Logger logger = LogManager.getLogger();

    public List<RoleDbEntity> getUserRoles(String userID) {
        List<RoleDbEntity> foundRoles = new LinkedList<>();

        try {
            foundRoles = roleDbRepo.getUserRolesByID(userID);
        } catch (IllegalArgumentException | OptimisticLockingFailureException error) {
            logger.error("Error in AuthorizationService::getUserRoles - failed to retrieve roles: " + error);
        }

        return foundRoles;
    }

    public List<PermissionDbEntity> getRolePermissions(Integer roleID) {
        List<PermissionDbEntity> foundPermissions = new LinkedList<>();

        try {
            foundPermissions = permissionDbRepo.getRolePermissions(roleID);
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            logger.error("Error in AuthorizationService::getRolePermissions - failed to retrieve permissions: " + error);
        }

        return foundPermissions;
    }

    public List<RoleDbEntity> getUserRolesByUsername(String username) {
        List<RoleDbEntity> foundRoles = new LinkedList<>();

        try {
            foundRoles = roleDbRepo.getUserRolesByUsername(username);
        } catch(Exception error) {
            logger.error("Error in AuthorizationService::getUserRolesByUsername - failed to retrieve roles;" + error.getMessage());
        }

        return foundRoles;
    }
}
