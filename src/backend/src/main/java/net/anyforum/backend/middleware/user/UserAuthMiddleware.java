package net.anyforum.backend.middleware.user;

import net.anyforum.backend.constants.AuthConstants;
import net.anyforum.backend.constants.MiddlewareMessage;
import net.anyforum.backend.models.api.auth.AuthRequestBody;
import net.anyforum.backend.models.database.UserDbEntity;
import net.anyforum.backend.services.user.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthMiddleware {
    @Autowired
    private UserDbService userDbService;

    public MiddlewareMessage verifyRegisterRequest(AuthRequestBody userCredentials) {
        String username = userCredentials.getUsername();
        String email = userCredentials.getEmail();
        String password = userCredentials.getPassword();

        if(username == null || email == null || password == null) {
            return MiddlewareMessage.MISSING_REQUEST_BODY_PARAMETERS;
        }

        if(!username.matches(AuthConstants.USERNAME_REGEX)) {
            return MiddlewareMessage.INVALID_USERNAME;
        }

        if(!email.matches(AuthConstants.EMAIL_REGEX)) {
            return MiddlewareMessage.INVALID_EMAIL;
        }

        if(userDbService.getUserByUsername(username).getUsername() != null) {
            return MiddlewareMessage.USERNAME_NOT_AVAILABLE;
        }

        if(userDbService.getUserByEmail(email).getEmail() != null) {
            return MiddlewareMessage.EMAIL_NOT_AVAILABLE;
        }

        if(!password.matches(AuthConstants.PASSWORD_REGEX)) {
            return MiddlewareMessage.INVALID_PASSWORD;
        }

        return MiddlewareMessage.OK;
    }

    public MiddlewareMessage verifyLoginRequest(AuthRequestBody userCredentials) {
        String email = userCredentials.getEmail();
        String password = userCredentials.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        UserDbEntity foundUser;

        if(email == null || password == null) {
            return MiddlewareMessage.MISSING_REQUEST_BODY_PARAMETERS;
        }

        if(!email.matches(AuthConstants.EMAIL_REGEX)) {
            return MiddlewareMessage.INVALID_EMAIL;
        }

        if(!password.matches(AuthConstants.PASSWORD_REGEX)) {
            return MiddlewareMessage.INVALID_PASSWORD;
        }

        foundUser = userDbService.getUserByEmail(email);

        if(foundUser.getId() == null) {
            return MiddlewareMessage.USER_NOT_FOUND;
        }

        if(!encoder.matches(password, foundUser.getSalt().concat(foundUser.getEncryptedPassword()))) {
            return MiddlewareMessage.INCORRECT_PASSWORD;
        }

        return MiddlewareMessage.OK;
    }
}
