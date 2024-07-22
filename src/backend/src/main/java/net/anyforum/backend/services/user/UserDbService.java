package net.anyforum.backend.services.user;

import net.anyforum.backend.constants.AuthConstants;
import net.anyforum.backend.exceptions.UserNotFoundException;
import net.anyforum.backend.models.api.user.UserAPIRequest;
import net.anyforum.backend.models.api.user.UserDataAPIResponse;
import net.anyforum.backend.models.database.user.UserDataDbEntity;
import net.anyforum.backend.models.database.user.UserDbEntity;
import net.anyforum.backend.models.dto.user.UserDTO;
import net.anyforum.backend.repos.user.UserDataDbRepo;
import net.anyforum.backend.repos.user.UserDbRepo;
import net.anyforum.backend.util.regex.RegexStringGenerator;
import net.anyforum.backend.util.time.DateHelper;
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

    public UserDbEntity getUserById(String id) {
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

    public UserDbEntity getUserByUsername(String username) {
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

    public UserDbEntity getUserByEmail(String email) {
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

    public String addUser(String username, String email, String password) {
        String id = RegexStringGenerator.generateString(AuthConstants.ID_REGEX);
        String encryptedPasswordFull = passwordEncoder.encode(password);
        String[] splitEncryptedPassword = encryptedPasswordFull.split("\\$");
        String salt = "$".concat(splitEncryptedPassword[1].concat("$".concat(splitEncryptedPassword[2].concat("$".concat(splitEncryptedPassword[3].substring(0, 22))))));
        String encryptedPassword = splitEncryptedPassword[splitEncryptedPassword.length - 1].substring(22);
        String todaysDate = DateHelper.getCurrentDate();

        try {
            userDbRepo.save(new UserDbEntity(id, username, email, salt, encryptedPassword));
            userDataDbRepo.save(new UserDataDbEntity(id, "", "", todaysDate));
        } catch(Exception error) {
            logger.error("Error in UserDbService::addUser - failed to add user to database: " + error);
            return "";
        }

        return id;
    }

    public UserDataDbEntity getUserDataById(String userID) {
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

    public UserDataAPIResponse getUserDataByUsername(String username) {
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

        return new UserDTO(foundUser, foundUserData).mapToAPIResponse();
    }

    public boolean updateUserData(UserAPIRequest newUserData, String userID) throws UserNotFoundException  {
        UserDataDbEntity foundUserData = userDataDbRepo.getUserByID(userID);
        UserDbEntity foundUser = userDbRepo.getUserById(userID);

        if(foundUserData == null || foundUserData.getUserID().equals("Not found.")) {
            throw new UserNotFoundException();
        }

        foundUser = new UserDTO().mapNewUserCredentials(newUserData, foundUser);
        foundUserData = new UserDTO().mapNewUserData(newUserData, foundUserData);

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