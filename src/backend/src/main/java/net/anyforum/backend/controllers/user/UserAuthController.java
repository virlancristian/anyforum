package net.anyforum.backend.controllers.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.anyforum.backend.constants.ResponseMessage;
import net.anyforum.backend.middleware.user.UserAuthMiddleware;
import net.anyforum.backend.models.api.auth.AuthJwtToken;
import net.anyforum.backend.models.api.auth.AuthRequestBody;
import net.anyforum.backend.models.api.auth.AuthResponse;
import net.anyforum.backend.services.user.UserAuthTokenDbService;
import net.anyforum.backend.services.user.UserDbService;
import net.anyforum.backend.util.jwt.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
public class UserAuthController {
    @Autowired
    private UserDbService userDbService;
    @Autowired
    private UserAuthTokenDbService userAuthTokenDbService;
    @Autowired
    private UserAuthMiddleware userAuthMiddleware;
    @Autowired
    private TokenUtil util;

    @CrossOrigin
    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody AuthRequestBody userCredentials) {
        ResponseMessage responseMessage = userAuthMiddleware.verifyRegisterRequest(userCredentials);

        if(responseMessage != ResponseMessage.OK) {
            return ResponseEntity.status(400).body(new AuthResponse(responseMessage.getMessage(), ""));
        }

        String userID = userDbService.addUser(userCredentials.getUsername(), userCredentials.getEmail(), userCredentials.getPassword());
        String token = userAuthTokenDbService.addAuthToken(userID);

        System.out.println(token);

        return ResponseEntity.status(200).body(new AuthResponse(util.generateToken(new AuthJwtToken(userID, token))));
    }
}
