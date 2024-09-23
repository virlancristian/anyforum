package net.anyforum.backend.services.session;

import net.anyforum.backend.models.session.SessionDbEntity;
import net.anyforum.backend.repos.session.SessionDbRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionHelperService {
    @Autowired
    private SessionDbRepo sessionDbRepo;
    private static final Logger logger = LogManager.getLogger();

    public SessionDbEntity getSessionByToken(String token) {
        SessionDbEntity foundSession;

        try {
            foundSession = sessionDbRepo.getSessionByToken(token);

            if(foundSession == null) {
                foundSession = new SessionDbEntity();
                foundSession.setSessionID("Not found.");
            }
        } catch(OptimisticLockingFailureException | IllegalArgumentException error) {
            logger.error("Error in SessionHelperService::getSessionByToken - failed to retrieve session: " + error);
            return null;
        }

        return foundSession;
    }

    public SessionDbEntity getSessionByUserID(String userID) {
        List<SessionDbEntity> foundSession;

        try {
            foundSession = sessionDbRepo.getSessionByUser(userID);

            if(foundSession == null || foundSession.isEmpty()) {
                return new SessionDbEntity("", "NOT_FOUND", "", "");
            }
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            logger.error("Error in SessionDbEntity::getSessionByID - failed to get session: " + error);
            return null;
        }

        return foundSession.get(0);
    }
}