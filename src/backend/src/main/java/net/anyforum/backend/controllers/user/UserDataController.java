package net.anyforum.backend.controllers.user;

import net.anyforum.backend.constants.MiddlewareMessage;
import net.anyforum.backend.exceptions.UserNotFoundException;
import net.anyforum.backend.middleware.common.CommonMiddleware;
import net.anyforum.backend.middleware.user.UserDataMiddleware;
import net.anyforum.backend.models.api.common.CommonAPIResponse;
import net.anyforum.backend.models.api.user.UserAPIRequest;
import net.anyforum.backend.models.api.user.UserDataAPIResponse;
import net.anyforum.backend.models.user.UserPublicData;
import net.anyforum.backend.models.user.UserPublicDataUpdate;
import net.anyforum.backend.services.user.UserDbService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<UserPublicData> getUserDataByUsername(@PathVariable("username") String username) {
        UserPublicData userPublicData = userDbService.getUserDataByUsername(username);

        if(userPublicData == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new UserPublicData());
        } else if(userPublicData.getId().equals("NOT_FOUND")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UserPublicData());
        }

        return ResponseEntity.status(HttpStatus.OK).body(userPublicData);
    }

    @CrossOrigin
    @PostMapping(value = "/{id}/public/update")
    public ResponseEntity<CommonAPIResponse> updateUserData(@PathVariable("id") String userID,
                                                            @RequestHeader("Authorization") String authorizationToken,
                                                            @RequestBody UserPublicDataUpdate userPublicDataUpdate) {
        MiddlewareMessage requestIntegrityMessage = commonMiddleware.verifyRequestIntegrity(userID, authorizationToken);
        MiddlewareMessage middlewareMessage = userDataMiddleware.verifyUserUpdateRequest(userPublicDataUpdate);
        boolean isOperationSuccessful;
        System.out.println(userPublicDataUpdate);
        if(requestIntegrityMessage != MiddlewareMessage.OK) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new CommonAPIResponse(middlewareMessage.getMessage()));
        }

        if(middlewareMessage != MiddlewareMessage.OK) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonAPIResponse(middlewareMessage.getMessage()));
        }

        try {
            isOperationSuccessful = userDbService.updateUserData(userPublicDataUpdate, userID);
        } catch(UserNotFoundException error) {
            logger.error("Error in UserDataController::updateUserData - failed to update user data: " + error);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CommonAPIResponse("User not found."));
        }

        return ResponseEntity
                .status(isOperationSuccessful ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new CommonAPIResponse(isOperationSuccessful ?
                        "User data modified successfully" :
                        "Internal server error."));
    }
}
