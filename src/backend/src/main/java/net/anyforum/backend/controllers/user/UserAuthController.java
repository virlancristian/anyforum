package net.anyforum.backend.controllers.user;

import net.anyforum.backend.constants.MiddlewareMessage;
import net.anyforum.backend.middleware.user.UserAuthMiddleware;
import net.anyforum.backend.models.api.auth.AuthRequestBody;
import net.anyforum.backend.models.api.auth.AuthResponse;
import net.anyforum.backend.models.user.UserLogin;
import net.anyforum.backend.models.user.UserSignup;
import net.anyforum.backend.services.authorization.AuthorizationHelperService;
import net.anyforum.backend.services.user.UserDbService;
import net.anyforum.backend.util.jwt.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<AuthResponse> registerUser(@RequestBody UserSignup userSignupData) {
        MiddlewareMessage responseMessage = userAuthMiddleware.verifyRegisterRequest(userSignupData);

        if(responseMessage != MiddlewareMessage.OK) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse(responseMessage.getMessage(), ""));
        }

        String userID = userDbService.addUser(userSignupData);
        boolean isMemberRoleAdded = authorizationHelperService.addMemberRole(userID);

        return ResponseEntity.
                status(!userID.isEmpty() && isMemberRoleAdded ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR).
                body(new AuthResponse(!userID.isEmpty() ? "" : "Internal server error", util.generateToken(userID)));
    }

    @CrossOrigin
    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserLogin userLoginData) {
        MiddlewareMessage message = userAuthMiddleware.verifyLoginRequest(userLoginData);

        if(message != MiddlewareMessage.OK) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse(message.getMessage(), ""));
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new AuthResponse("", util.generateToken(userDbService.
                                                                        getUserByEmail(userLoginData.getEmail()).getId())));
    }
}
