package net.anyforum.backend.controllers.session;

import net.anyforum.backend.constants.MiddlewareMessage;
import net.anyforum.backend.middleware.session.SessionMiddleware;
import net.anyforum.backend.models.api.session.SessionAPIRequest;
import net.anyforum.backend.models.api.session.SessionAPIResponse;
import net.anyforum.backend.models.api.session.SessionJwtToken;
import net.anyforum.backend.models.database.session.SessionDbEntity;
import net.anyforum.backend.models.database.user.UserDbEntity;
import net.anyforum.backend.services.session.SessionDbService;
import net.anyforum.backend.services.user.UserDbService;
import net.anyforum.backend.util.jwt.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/session")
public class SessionController {
    @Autowired
    private SessionDbService sessionDbService;
    @Autowired
    private SessionMiddleware sessionMiddleware;
    @Autowired
    private UserDbService userDbService;
    @Autowired
    private TokenUtil tokenUtil;

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<SessionAPIResponse> createSession(@RequestBody SessionAPIRequest requestBody) {
        String userID = requestBody.getUserID();
        MiddlewareMessage message = sessionMiddleware.verifyCreateRequest(userID);
        SessionDbEntity newSession;
        UserDbEntity foundUser;
        SessionJwtToken sessionJwtToken;

        if(message != MiddlewareMessage.OK) {
            return ResponseEntity.status(400).body(new SessionAPIResponse(message.getMessage()));
        }

        newSession = sessionDbService.createSession(userID);
        foundUser = userDbService.getUserById(userID);

        if(newSession == null) {
            return ResponseEntity.status(500).body(new SessionAPIResponse("Internal server error"));
        }

        sessionJwtToken = new SessionJwtToken(foundUser.getUsername(),
                                                userID,
                                                foundUser.getEmail(),
                                                foundUser.isMuted(),
                                                foundUser.isBanned(),
                                                foundUser.isNsfwOn(),
                                                newSession.getSessionID(),
                                                newSession.getSessionToken(),
                                                newSession.getExpiresAt());

        return ResponseEntity.status(200).body(new SessionAPIResponse("Session created successfully.", tokenUtil.generateToken(sessionJwtToken)));
    }

    @CrossOrigin
    @PostMapping("/get")
    public ResponseEntity<SessionAPIResponse> getSession(@RequestBody SessionAPIRequest requestBody) {
        String sessionID = requestBody.getSessionID();
        MiddlewareMessage message = sessionMiddleware.verifyGetRequest(sessionID);
        SessionDbEntity foundSession;
        UserDbEntity foundUser;
        SessionJwtToken token;

        if(message != MiddlewareMessage.OK) {
            return ResponseEntity.status(400).body(new SessionAPIResponse(message.getMessage()));
        }

        foundSession = sessionDbService.getSessionByID(sessionID);
        foundUser = userDbService.getUserById(foundSession.getUserID());
        token = new SessionJwtToken(foundUser.getUsername(),
                                    foundUser.getId(),
                                    foundUser.getEmail(),
                                    foundUser.isMuted(),
                                    foundUser.isBanned(),
                                    foundUser.isNsfwOn(),
                                    foundSession.getSessionID(),
                                    foundSession.getSessionToken(),
                                    foundSession.getExpiresAt());

        return ResponseEntity.status(200).body(new SessionAPIResponse("Session retrieved successfully.", tokenUtil.generateToken(token)));
    }

    @CrossOrigin
    @PostMapping("/delete")
    public ResponseEntity<SessionAPIResponse> deleteSession(@RequestBody SessionAPIRequest requestBody) {
        String sessionID = requestBody.getSessionID();
        MiddlewareMessage message = sessionMiddleware.verifyGetRequest(sessionID);
        boolean isDeleted;

        if(message != MiddlewareMessage.OK) {
            return ResponseEntity.status(400).body(new SessionAPIResponse(message.getMessage()));
        }

        isDeleted = sessionDbService.deleteSession(sessionID);

        return ResponseEntity.status(isDeleted ? 200 : 500).body(new SessionAPIResponse(isDeleted ? "Session deleted successfully." : "There was an error while trying to delete the session."));
    }
}
