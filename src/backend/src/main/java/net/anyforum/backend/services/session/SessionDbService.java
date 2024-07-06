package net.anyforum.backend.services.session;

import net.anyforum.backend.constants.SessionConstants;
import net.anyforum.backend.models.database.SessionDbEntity;
import net.anyforum.backend.repos.session.SessionDbRepo;
import net.anyforum.backend.util.regex.RegexStringGenerator;
import net.anyforum.backend.util.time.DateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@Service
public class SessionDbService {
    @Autowired
    private SessionDbRepo sessionDbRepo;

    public SessionDbEntity getSessionByID(String sessionID) {
        SessionDbEntity foundSession;

        try {
            foundSession = sessionDbRepo.getSessionByID(sessionID);

            if(foundSession == null) {
                foundSession = new SessionDbEntity("", "", "", "");
            }
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            System.out.println("Error in SessionDbEntity::getSessionByID - failed to get session: " + error);
            foundSession = new SessionDbEntity("", "", "", "");
        }

        return foundSession;
    }

    public SessionDbEntity getSessionByUser(String userID) {
        SessionDbEntity foundSession;

        try {
            foundSession = sessionDbRepo.getSessionByUser(userID);

            if(foundSession == null) {
                foundSession = new SessionDbEntity("", "", "", "");
            }
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            System.out.println("Error in SessionDbEntity::getSessionByID - failed to get session: " + error);
            foundSession = new SessionDbEntity("", "", "", "");
        }

        return foundSession;
    }

    public SessionDbEntity createSession(String userID) {
        String sessionID = RegexStringGenerator.generateString(SessionConstants.SESSION_ID_REGEX);
        String sessionToken = RegexStringGenerator.generateString(SessionConstants.SESSION_TOKEN_REGEX);
        String expiresAt = DateHelper.getFutureDate(365);
        SessionDbEntity newSession = new SessionDbEntity(sessionID, userID, sessionToken, expiresAt);

        try {
            sessionDbRepo.save(newSession);
        } catch (IllegalArgumentException | OptimisticLockingFailureException error) {
            System.out.println("Error in SessionDbService::createSession - failed to create session: " + error);
            return null;
        }

        return newSession;
    }

    public boolean deleteSession(String sessionID) {
        SessionDbEntity foundSession = getSessionByID(sessionID);

        try {
            sessionDbRepo.delete(foundSession);
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            System.out.println("Error in SessionDbService::deleteSession - unable to delete session: " + error);
            return false;
        }

        return true;
    }
}
