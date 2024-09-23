package net.anyforum.backend.services.user;

import net.anyforum.backend.models.user.UserDTO;
import net.anyforum.backend.models.user.UserDataDbEntity;
import net.anyforum.backend.models.user.UserDbEntity;
import net.anyforum.backend.models.user.UserSessionData;
import net.anyforum.backend.repos.user.UserDataDbRepo;
import net.anyforum.backend.repos.user.UserDbRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class UserHelperService {
    private UserDbRepo userDbRepo;
    private UserDataDbRepo userDataDbRepo;
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    public UserHelperService(UserDbRepo userDbRepo, UserDataDbRepo userDataDbRepo) {
        this.userDbRepo = userDbRepo;
        this.userDataDbRepo = userDataDbRepo;
    }

    public UserDbEntity getUserByUsername(String username) {
        try {
            return userDbRepo.getUserByUsername(username);
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            logger.error("Error in UserHelperService::getUserByUsername - failed to retrieve user: " + error.getMessage());
            return null;
        }
    }

    public UserSessionData getUserSessionData(String userID) {
        UserSessionData userSessionData;

        try {
            UserDbEntity foundUser = userDbRepo.getUserById(userID);
            UserDataDbEntity foundUserData = userDataDbRepo.getUserByID(userID);
            userSessionData = UserDTO.mapToUserSessionData(foundUser, foundUserData);
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            logger.error("Error in UserHelperService::getUSerSessionData - failed to retrieve user session data: " + error.getMessage());
            return null;
        }

        return userSessionData;
    }
}
