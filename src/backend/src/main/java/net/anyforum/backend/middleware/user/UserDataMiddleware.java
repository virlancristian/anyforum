package net.anyforum.backend.middleware.user;

import net.anyforum.backend.constants.AuthConstants;
import net.anyforum.backend.constants.MiddlewareMessage;
import net.anyforum.backend.middleware.session.SessionMiddleware;
import net.anyforum.backend.models.api.user.UserAPIRequest;
import net.anyforum.backend.models.database.session.SessionDbEntity;
import net.anyforum.backend.models.database.user.UserDbEntity;
import net.anyforum.backend.services.session.SessionHelperService;
import net.anyforum.backend.services.user.UserDbService;
import net.anyforum.backend.services.user.UserHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDataMiddleware {
    private UserHelperService userHelperService;

    @Autowired
    public UserDataMiddleware(UserHelperService userHelperService) {
        this.userHelperService = userHelperService;
    }

    public MiddlewareMessage verifyUserUpdateRequest(UserAPIRequest requestBody) {
        if(requestBody == null) {
            return MiddlewareMessage.MISSING_REQUEST_BODY_PARAMETERS;
        }

        if(requestBody.getUsername() != null && !requestBody.getUsername().matches(AuthConstants.USERNAME_REGEX)) {
            return MiddlewareMessage.INVALID_USERNAME;
        }

        if(userHelperService.getUserByUsername(requestBody.getUsername()) != null) {
            return MiddlewareMessage.USERNAME_NOT_AVAILABLE;
        }

        return MiddlewareMessage.OK;
    }
}
