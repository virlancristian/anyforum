package net.anyforum.backend.services.session;

import net.anyforum.backend.models.session.SessionDbEntity;
import net.anyforum.backend.models.session.SessionDTO;
import net.anyforum.backend.repos.session.SessionDbRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionDbService {
    @Autowired
    private SessionDbRepo sessionDbRepo;
    private static final Logger logger = LogManager.getLogger();

    public SessionDbEntity getSessionByID(String sessionID) {
        SessionDbEntity foundSession;

        try {
            foundSession = sessionDbRepo.getSessionByID(sessionID);

            if(foundSession == null) {
                foundSession = new SessionDbEntity("", "", "", "");
            }
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            logger.error("Error in SessionDbEntity::getSessionByID - failed to get session: " + error);
            foundSession = new SessionDbEntity("", "", "", "");
        }

        return foundSession;
    }

    public SessionDbEntity getSessionByUser(String userID) {
        List<SessionDbEntity> foundSession;

        try {
            foundSession = sessionDbRepo.getSessionByUser(userID);

            if(foundSession == null || foundSession.isEmpty()) {
                return new SessionDbEntity("", "", "", "");
            }
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            logger.error("Error in SessionDbEntity::getSessionByID - failed to get session: " + error);
            return new SessionDbEntity("", "", "", "");
        }

        return foundSession.get(0);
    }

    public SessionDbEntity createSession(String userID) {
        SessionDbEntity newSession = SessionDTO.mapToSessionDbEntity(userID);

        try {
            sessionDbRepo.save(newSession);
        } catch (IllegalArgumentException | OptimisticLockingFailureException error) {
            logger.error("Error in SessionDbService::createSession - failed to create session: " + error);
            return null;
        }

        return newSession;
    }

    public boolean deleteSession(String sessionID) {
        SessionDbEntity foundSession = getSessionByID(sessionID);

        try {
            sessionDbRepo.delete(foundSession);
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            logger.error("Error in SessionDbService::deleteSession - unable to delete session: " + error);
            return false;
        }

        return true;
    }

    public SessionDbEntity getSessionByToken(String token) {
        SessionDbEntity foundSession;

        try {
            foundSession = sessionDbRepo.getSessionByToken(token);
        } catch(IllegalArgumentException | OptimisticLockingFailureException error) {
            logger.error("Error in SessionDbService::getSessionByToken - failed to retrieve session: " + error);
            foundSession = null;
        }

        return foundSession;
    }
}
