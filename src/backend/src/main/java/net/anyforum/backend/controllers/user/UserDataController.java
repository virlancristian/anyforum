package net.anyforum.backend.controllers.user;

import net.anyforum.backend.constants.MiddlewareMessage;
import net.anyforum.backend.exceptions.UserNotFoundException;
import net.anyforum.backend.middleware.common.CommonMiddleware;
import net.anyforum.backend.middleware.user.UserDataMiddleware;
import net.anyforum.backend.models.api.common.CommonAPIResponse;
import net.anyforum.backend.models.api.user.UserAPIRequest;
import net.anyforum.backend.models.api.user.UserDataAPIResponse;
import net.anyforum.backend.services.user.UserDbService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserDataController {
    @Autowired
    private UserDbService userDbService;
    @Autowired
    private UserDataMiddleware userDataMiddleware;
    @Autowired
    private CommonMiddleware commonMiddleware;
    private static final Logger logger = LogManager.getLogger();

    @CrossOrigin
    @GetMapping("/{username}/public")
    public ResponseEntity<UserDataAPIResponse> getUserDataByUsername(@PathVariable("username") String username) {
        UserDataAPIResponse apiResponse = userDbService.getUserDataByUsername(username);

        if(apiResponse == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserDataAPIResponse());
        } else if(apiResponse.getUserID().equals("NOT_FOUND")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserDataAPIResponse());
        }

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @CrossOrigin
    @PostMapping("/{id}/public/update")
    public ResponseEntity<CommonAPIResponse> updateUserData(@PathVariable("id") String userID,
                                                            @RequestHeader("Authorization") String authorizationToken,
                                                            @RequestBody(required = false) UserAPIRequest body) {
        MiddlewareMessage requestIntegrityMessage = commonMiddleware.verifyRequestIntegrity(userID, authorizationToken);
        MiddlewareMessage middlewareMessage = userDataMiddleware.verifyUserUpdateRequest(body);
        boolean operationSuccessful;

        if(requestIntegrityMessage != MiddlewareMessage.OK) {
            return ResponseEntity.status(400).body(new CommonAPIResponse(middlewareMessage.getMessage()));
        }

        if(middlewareMessage != MiddlewareMessage.OK) {
            return ResponseEntity.status(400).body(new CommonAPIResponse(middlewareMessage.getMessage()));
        }

        try {
            operationSuccessful = userDbService.updateUserData(body, userID);
        } catch(UserNotFoundException error) {
            logger.error("Error in UserDataController::updateUserData - failed to update user data: " + error);
            return ResponseEntity.status(404).body(new CommonAPIResponse("User not found."));
        }

        return ResponseEntity
                .status(operationSuccessful ? 200 : 500)
                .body(new CommonAPIResponse(operationSuccessful ?
                        "User data modified successfully" :
                        "Internal server error."));
    }
}
