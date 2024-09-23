package net.anyforum.backend.controllers.session;

import net.anyforum.backend.constants.MiddlewareMessage;
import net.anyforum.backend.middleware.session.SessionMiddleware;
import net.anyforum.backend.models.session.SessionAPIRequest;
import net.anyforum.backend.models.session.SessionAPIResponse;
import net.anyforum.backend.models.session.SessionDTO;
import net.anyforum.backend.models.session.SessionJwtToken;
import net.anyforum.backend.models.session.SessionDbEntity;
import net.anyforum.backend.models.user.UserSessionData;
import net.anyforum.backend.services.session.SessionDbService;
import net.anyforum.backend.services.user.UserHelperService;
import net.anyforum.backend.util.jwt.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private UserHelperService userHelperService;
    @Autowired
    private TokenUtil tokenUtil;

    @CrossOrigin
    @PostMapping("/create")
    public ResponseEntity<SessionAPIResponse> createSession(@RequestBody SessionAPIRequest requestBody) {
        String userID = requestBody.getUserID();
        MiddlewareMessage message = sessionMiddleware.verifyCreateRequest(userID);
        SessionDbEntity newSession;
        UserSessionData userSessionData;
        SessionJwtToken sessionJwtToken;

        if(message != MiddlewareMessage.OK) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SessionAPIResponse(message.getMessage()));
        }

        newSession = sessionDbService.createSession(userID);
        userSessionData = userHelperService.getUserSessionData(userID);

        if(newSession == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new SessionAPIResponse("Internal server error"));
        }

        sessionJwtToken = SessionDTO.mapToSessionJwtToken(newSession, userSessionData);

        return ResponseEntity.status(HttpStatus.OK).body(new SessionAPIResponse("Session created successfully.", tokenUtil.generateToken(sessionJwtToken)));
    }

    @CrossOrigin
    @PostMapping("/get")
    public ResponseEntity<SessionAPIResponse> getSession(@RequestBody SessionAPIRequest requestBody) {
        String sessionID = requestBody.getSessionID();
        MiddlewareMessage message = sessionMiddleware.verifyGetRequest(sessionID);
        SessionDbEntity foundSession;
        UserSessionData userSessionData;
        SessionJwtToken token;

        if(message != MiddlewareMessage.OK) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SessionAPIResponse(message.getMessage()));
        }

        foundSession = sessionDbService.getSessionByID(sessionID);
        userSessionData = userHelperService.getUserSessionData(foundSession.getUserID());
        token = SessionDTO.mapToSessionJwtToken(foundSession, userSessionData);

        return ResponseEntity.status(HttpStatus.OK).body(new SessionAPIResponse("Session retrieved successfully.", tokenUtil.generateToken(token)));
    }

    @CrossOrigin
    @PostMapping("/delete")
    public ResponseEntity<SessionAPIResponse> deleteSession(@RequestBody SessionAPIRequest requestBody) {
        String sessionID = requestBody.getSessionID();
        MiddlewareMessage message = sessionMiddleware.verifyGetRequest(sessionID);
        boolean isDeleted;

        if(message != MiddlewareMessage.OK) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SessionAPIResponse(message.getMessage()));
        }

        isDeleted = sessionDbService.deleteSession(sessionID);

        return ResponseEntity.status(isDeleted ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).body(new SessionAPIResponse(isDeleted ? "Session deleted successfully." : "There was an error while trying to delete the session."));
    }
}
