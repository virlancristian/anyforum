package net.anyforum.backend.models.session;

import net.anyforum.backend.constants.SessionConstants;
import net.anyforum.backend.models.user.UserSessionData;
import net.anyforum.backend.util.regex.RegexStringGenerator;
import net.anyforum.backend.util.time.DateHelper;

public class SessionDTO {
    public static SessionDbEntity mapToSessionDbEntity(String userID) {
        String generatedSessionID = RegexStringGenerator.generateString(SessionConstants.SESSION_ID_REGEX);
        String generatedSessionToken = RegexStringGenerator.generateString(SessionConstants.SESSION_TOKEN_REGEX);
        String expiresAt = DateHelper.getFutureDate(365);

        return new SessionDbEntity(generatedSessionID, userID, generatedSessionToken, expiresAt);
    }

    public static SessionJwtToken mapToSessionJwtToken(SessionDbEntity sessionDbEntity, UserSessionData userSessionData) {
        return new SessionJwtToken(
                userSessionData.getUsername(),
                userSessionData.getId(),
                userSessionData.isMuted(),
                userSessionData.isBanned(),
                userSessionData.isNSFWOn(),
                sessionDbEntity.getSessionID(),
                sessionDbEntity.getSessionToken(),
                sessionDbEntity.getExpiresAt()
        );
    }
}
