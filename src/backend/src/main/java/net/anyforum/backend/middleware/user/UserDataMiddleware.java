package net.anyforum.backend.middleware.user;

import net.anyforum.backend.constants.AuthConstants;
import net.anyforum.backend.constants.MiddlewareMessage;
import net.anyforum.backend.models.api.user.UserAPIRequest;
import net.anyforum.backend.models.user.UserPublicDataUpdate;
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

    public MiddlewareMessage verifyUserUpdateRequest(UserPublicDataUpdate userPublicDataUpdate) {
        if(userPublicDataUpdate == null) {
            return MiddlewareMessage.MISSING_REQUEST_BODY_PARAMETERS;
        }

        if(userPublicDataUpdate.getUsername() != null && !userPublicDataUpdate.getUsername().matches(AuthConstants.USERNAME_REGEX)) {
            return MiddlewareMessage.INVALID_USERNAME;
        }

        if(userHelperService.getUserByUsername(userPublicDataUpdate.getUsername()) != null) {
            return MiddlewareMessage.USERNAME_NOT_AVAILABLE;
        }

        return MiddlewareMessage.OK;
    }
}
