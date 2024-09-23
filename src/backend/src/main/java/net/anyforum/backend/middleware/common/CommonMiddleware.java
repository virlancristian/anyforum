package net.anyforum.backend.middleware.common;

import net.anyforum.backend.constants.MiddlewareMessage;
import net.anyforum.backend.models.session.SessionDbEntity;
import net.anyforum.backend.services.session.SessionHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommonMiddleware {
    private SessionHelperService sessionHelperService;

    @Autowired
    public CommonMiddleware(SessionHelperService sessionHelperService) {
        this.sessionHelperService = sessionHelperService;
    }

    public MiddlewareMessage verifyRequestIntegrity(String userID, String authorizationToken) {
        MiddlewareMessage userIntegrityMessage = verifyUserIntegrity(userID);
        MiddlewareMessage sessionIntegrityMessage = verifySessionIntegrity(authorizationToken, userID);

        if(userIntegrityMessage != MiddlewareMessage.OK) {
            return userIntegrityMessage;
        }

        if(sessionIntegrityMessage != MiddlewareMessage.OK) {
            return sessionIntegrityMessage;
        }

        return MiddlewareMessage.OK;
    }

    private MiddlewareMessage verifyUserIntegrity(String userID) {
        SessionDbEntity foundSession = sessionHelperService.getSessionByUserID(userID);

        return foundSession != null ? MiddlewareMessage.OK : MiddlewareMessage.USER_NOT_FOUND;
    }

    private MiddlewareMessage verifySessionIntegrity(String authorizationToken, String userID) {
        MiddlewareMessage sessionMiddlewareResponse = verifyTokenRequest(authorizationToken);
        String[] authorizationTokenParts = authorizationToken.split(" +");
        SessionDbEntity foundSession;

        if(sessionMiddlewareResponse != MiddlewareMessage.OK) {
            return sessionMiddlewareResponse;
        }

        foundSession = sessionHelperService.getSessionByToken(authorizationTokenParts[1]);

        if(!foundSession.getUserID().equals(userID)) {
            return MiddlewareMessage.SESSION_TOKEN_NOT_MATCHING;
        }

        return MiddlewareMessage.OK;
    }

    private MiddlewareMessage verifyTokenRequest(String sessionToken) {
        String[] splitSessionToken;     //Session token is formatted like this: AnyForumToken {sessionToken}

        if(sessionToken == null) {
            return MiddlewareMessage.MISSING_AUTHORIZATION_TOKEN;
        }

        splitSessionToken = sessionToken.split(" +");

        if(!splitSessionToken[0].equals("AnyTopicToken")) {
            return MiddlewareMessage.INVALID_AUTHORIZATION_TOKEN;
        }

        return sessionHelperService.getSessionByToken(splitSessionToken[1]) != null ? MiddlewareMessage.OK : MiddlewareMessage.INVALID_AUTHORIZATION_TOKEN;
    }
}
