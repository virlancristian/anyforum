package net.anyforum.backend.middleware.user;

import net.anyforum.backend.constants.AuthConstants;
import net.anyforum.backend.constants.ResponseMessage;
import net.anyforum.backend.models.api.auth.AuthRequestBody;
import net.anyforum.backend.models.api.auth.AuthResponse;
import net.anyforum.backend.models.database.UserDbEntity;
import net.anyforum.backend.services.user.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserAuthMiddleware {
    @Autowired
    private UserDbService userDbService;

    public ResponseMessage verifyRegisterRequest(AuthRequestBody userCredentials) {
        String username = userCredentials.getUsername();
        String email = userCredentials.getEmail();
        String password = userCredentials.getPassword();

        if(username == null || email == null || password == null) {
            return ResponseMessage.MISSING_REQUEST_BODY_PARAMETERS;
        }

        if(!username.matches(AuthConstants.USERNAME_REGEX)) {
            return ResponseMessage.INVALID_USERNAME;
        }

        if(!email.matches(AuthConstants.EMAIL_REGEX)) {
            return ResponseMessage.INVALID_EMAIL;
        }

        if(userDbService.getUserByUsername(username).getUsername() != null) {
            return ResponseMessage.USERNAME_NOT_AVAILABLE;
        }

        if(userDbService.getUserByEmail(email).getEmail() != null) {
            return ResponseMessage.EMAIL_NOT_AVAILABLE;
        }

        if(!password.matches(AuthConstants.PASSWORD_REGEX)) {
            return ResponseMessage.INVALID_PASSWORD;
        }

        return ResponseMessage.OK;
    }
}
