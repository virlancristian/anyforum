package net.anyforum.backend.services.user;

import net.anyforum.backend.constants.AuthConstants;
import net.anyforum.backend.models.database.UserDbEntity;
import net.anyforum.backend.repos.user.UserDbRepo;
import net.anyforum.backend.util.regex.RegexStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDbService {
    @Autowired
    private UserDbRepo userDbRepo;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserDbEntity getUserById(String id) {
        UserDbEntity foundUser;

        try {
            foundUser = userDbRepo.getUserById(id);

            if(foundUser == null) {
                return new UserDbEntity();
            }
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            System.out.println("Error in UserDbService::getUserById - unable to get user: " + error);
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
            System.out.println("Error in UserDbService::getUserByUsername - unable to get user: " + error);
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
            System.out.println("Error in UserDbService::getUserByEmail - unable to get user: " + error);
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

        try {
            userDbRepo.save(new UserDbEntity(id, username, email, salt, encryptedPassword));
        } catch(Exception error) {
            System.out.println("Error in UserDbService::addUser - failed to add user to database: " + error);
            return "";
        }

        return id;
    }
}
