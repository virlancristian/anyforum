package net.anyforum.backend.middleware.session;

import net.anyforum.backend.constants.AuthConstants;
import net.anyforum.backend.constants.MiddlewareMessage;
import net.anyforum.backend.constants.SessionConstants;
import net.anyforum.backend.services.session.SessionDbService;
import net.anyforum.backend.services.user.UserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionMiddleware {
    @Autowired
    private UserDbService userDbService;
    @Autowired
    private SessionDbService sessionDbService;

    public MiddlewareMessage verifyCreateRequest(String userID) {
        if(userID == null) {
            return MiddlewareMessage.MISSING_REQUEST_BODY_PARAMETERS;
        }

        if(!userID.matches(AuthConstants.ID_REGEX)) {
            return MiddlewareMessage.INVALID_USER_ID;
        }

        if(userDbService.getUserById(userID).getUsername() == null) {
            return MiddlewareMessage.USER_NOT_FOUND;
        }

        return MiddlewareMessage.OK;
    }

    public MiddlewareMessage verifyGetRequest(String sessionID) {
        if(sessionID == null) {
            return MiddlewareMessage.MISSING_REQUEST_BODY_PARAMETERS;
        }

        if(!sessionID.matches(SessionConstants.SESSION_ID_REGEX)) {
            return MiddlewareMessage.INVALID_SESSION_ID;
        }

        if(sessionDbService.getSessionByID(sessionID).getUserID().isEmpty()) {
            return MiddlewareMessage.SESSION_NOT_FOUND;
        }

        return MiddlewareMessage.OK;
    }
}
