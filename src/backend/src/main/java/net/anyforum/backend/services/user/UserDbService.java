package net.anyforum.backend.services.user;

import com.sun.istack.NotNull;
import net.anyforum.backend.exceptions.UserNotFoundException;
import net.anyforum.backend.models.user.*;
import net.anyforum.backend.repos.user.UserDataDbRepo;
import net.anyforum.backend.repos.user.UserDbRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDbService {
    @Autowired
    private UserDbRepo userDbRepo;
    @Autowired
    private UserDataDbRepo userDataDbRepo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static final Logger logger = LogManager.getLogger();

    public UserDbEntity getUserById(@NotNull String id) {
        UserDbEntity foundUser;

        try {
            foundUser = userDbRepo.getUserById(id);

            if(foundUser == null) {
                return new UserDbEntity();
            }
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            logger.error("Error in UserDbService::getUserById - unable to get user: " + error);
            return new UserDbEntity();
        }

        return foundUser;
    }

    public UserDbEntity getUserByUsername(@NotNull String username) {
        UserDbEntity foundUser;

        try {
            foundUser = userDbRepo.getUserByUsername(username);

            if(foundUser == null) {
                return new UserDbEntity();
            }
        } catch (IllegalArgumentException | OptimisticLockingFailureException error) {
            logger.error("Error in UserDbService::getUserByUsername - unable to get user: " + error);
            return new UserDbEntity();
        }

        return foundUser;
    }

    public UserDbEntity getUserByEmail(@NotNull String email) {
        UserDbEntity foundUser;

        try {
            foundUser = userDbRepo.getUserByEmail(email);

            if(foundUser == null) {
                return new UserDbEntity();
            }
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            logger.error("Error in UserDbService::getUserByEmail - unable to get user: " + error);
            return new UserDbEntity();
        }

        return foundUser;
    }

    public String addUser(@NotNull UserSignup userSignupData) {
        UserDbEntity newUserDbEntity = UserDTO.mapToUserDbEntity(userSignupData);
        UserDataDbEntity newUserDataDbEntity = UserDTO.mapToUserDataDbEntity(newUserDbEntity.getId());

        try {
            userDbRepo.save(newUserDbEntity);
            userDataDbRepo.save(newUserDataDbEntity);
        } catch(Exception error) {
            logger.error("Error in UserDbService::addUser - failed to add user to database: " + error);
            return "";
        }

        return newUserDbEntity.getId();
    }

    public UserDataDbEntity getUserDataById(@NotNull String userID) {
        UserDataDbEntity foundUserData;

        try {
            foundUserData = userDataDbRepo.getUserByID(userID);
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            logger.error("Error in UserDbService::getUserData - failed to get user data: " + error);
            return null;
        }

        if(foundUserData == null) {
            foundUserData = new UserDataDbEntity();
            foundUserData.setUserID("Not found");
        }

        return foundUserData;
    }

    public UserPublicData getUserDataByUsername(@NotNull String username) {
        UserDataDbEntity foundUserData;
        UserDbEntity foundUser;

        try {
            foundUserData = userDataDbRepo.getUserByUsername(username);
            foundUser = userDbRepo.getUserByUsername(username);

            if(foundUser == null || foundUserData == null) {
                foundUser = new UserDbEntity();
                foundUserData = new UserDataDbEntity();

                foundUser.setId("NOT_FOUND");
            }
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            logger.error("Error in UserDbService::getDataByUsername - failed to query user data: " + error.getMessage());
            return null;
        }

        return UserDTO.mapToUserPublicData(foundUser, foundUserData);
    }

    public boolean updateUserData(UserPublicDataUpdate userPublicDataUpdate, String userID) throws UserNotFoundException  {
        UserDataDbEntity foundUserData = userDataDbRepo.getUserByID(userID);
        UserDbEntity foundUser = userDbRepo.getUserById(userID);

        if(foundUserData == null || foundUser == null) {
            throw new UserNotFoundException();
        }

        foundUserData = UserDTO.mapUpdatedUserData(userPublicDataUpdate, foundUserData);
        foundUser = UserDTO.mapUpdatedUserDbEntity(userPublicDataUpdate, foundUser);

        try {
            userDataDbRepo.save(foundUserData);
            userDbRepo.save(foundUser);
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            logger.error("Error in UserDbService::updateUserData - failed to update user data in db: " + error);
            return false;
        }

        return true;
    }
}