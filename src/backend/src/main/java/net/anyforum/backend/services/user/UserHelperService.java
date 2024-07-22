package net.anyforum.backend.services.user;

import net.anyforum.backend.models.database.user.UserDbEntity;
import net.anyforum.backend.repos.user.UserDbRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class UserHelperService {
    private UserDbRepo userDbRepo;
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    public UserHelperService(UserDbRepo userDbRepo) {
        this.userDbRepo = userDbRepo;
    }

    public UserDbEntity getUserByUsername(String username) {
        try {
            return userDbRepo.getUserByUsername(username);
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            logger.error("Error in UserHelperService::getUserByUsername - failed to retrieve user: " + error.getMessage());
            return null;
        }
    }
}
