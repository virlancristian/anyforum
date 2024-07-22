package net.anyforum.backend.controllers.user;

import net.anyforum.backend.constants.MiddlewareMessage;
import net.anyforum.backend.middleware.user.UserAuthMiddleware;
import net.anyforum.backend.models.api.auth.AuthRequestBody;
import net.anyforum.backend.models.api.auth.AuthResponse;
import net.anyforum.backend.services.authorization.AuthorizationHelperService;
import net.anyforum.backend.services.user.UserDbService;
import net.anyforum.backend.util.jwt.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserAuthController {
    @Autowired
    private UserDbService userDbService;
    @Autowired
    private AuthorizationHelperService authorizationHelperService;
    @Autowired
    private UserAuthMiddleware userAuthMiddleware;
    @Autowired
    private TokenUtil util;

    @CrossOrigin
    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody AuthRequestBody userCredentials) {
        MiddlewareMessage responseMessage = userAuthMiddleware.verifyRegisterRequest(userCredentials);

        if(responseMessage != MiddlewareMessage.OK) {
            return ResponseEntity.status(400).body(new AuthResponse(responseMessage.getMessage(), ""));
        }

        String userID = userDbService.addUser(userCredentials.getUsername(), userCredentials.getEmail(), userCredentials.getPassword());
        boolean isMemberRoleAdded = authorizationHelperService.addMemberRole(userID);

        return ResponseEntity.
                status(!userID.isEmpty() && isMemberRoleAdded ? 200 : 500).
                body(new AuthResponse(!userID.isEmpty() ? "" : "Internal server error", util.generateToken(userID)));
    }

    @CrossOrigin
    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequestBody userCredentials) {
        MiddlewareMessage message = userAuthMiddleware.verifyLoginRequest(userCredentials);

        if(message != MiddlewareMessage.OK) {
            return ResponseEntity.status(400).body(new AuthResponse(message.getMessage(), ""));
        }

        return ResponseEntity
                .status(200)
                .body(new AuthResponse("", util.generateToken(userDbService.
                                                                        getUserByEmail(userCredentials.
                                                                                                        getEmail()).getId())));
    }
}
