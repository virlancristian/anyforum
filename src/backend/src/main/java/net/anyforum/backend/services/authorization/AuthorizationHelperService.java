package net.anyforum.backend.services.authorization;

import net.anyforum.backend.models.database.authorization.UserRoleDbEntity;
import net.anyforum.backend.models.database.authorization.UserRoleID;
import net.anyforum.backend.repos.role.RolePermissionDbRepo;
import net.anyforum.backend.repos.role.UserRoleDbRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationHelperService {
    @Autowired
    private RolePermissionDbRepo rolePermissionDbRepo;
    @Autowired
    private UserRoleDbRepo userRoleDbRepo;
    private static final Logger logger = LogManager.getLogger();

    public boolean addMemberRole(String userID) {
        UserRoleID userRoleID = new UserRoleID(userID, 6);
        UserRoleDbEntity newUserRoleEntity = new UserRoleDbEntity(userRoleID);
        boolean operationSuccessful;

        try {
            userRoleDbRepo.save(newUserRoleEntity);
            operationSuccessful = true;
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            logger.error("Error in AuthorizationHelperService::addMemberRole - failed to add member role: " + error);
            operationSuccessful = false;
        }

        return operationSuccessful;
    }
}
