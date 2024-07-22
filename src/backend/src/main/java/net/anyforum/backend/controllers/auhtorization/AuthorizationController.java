package net.anyforum.backend.controllers.auhtorization;

import net.anyforum.backend.constants.AuthConstants;
import net.anyforum.backend.middleware.session.SessionMiddleware;
import net.anyforum.backend.models.api.authorization.AuthorizationAPIResponse;
import net.anyforum.backend.models.database.authorization.RoleDbEntity;
import net.anyforum.backend.services.authorization.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class AuthorizationController {
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private SessionMiddleware sessionMiddleware;

    @CrossOrigin
    @GetMapping("/api/roles/user/{identifier}")
    public ResponseEntity<AuthorizationAPIResponse> getUserRoles(@PathVariable("identifier") String identifier) {
        List<RoleDbEntity> foundRoles = identifier.matches(AuthConstants.ID_REGEX) ?
                                        authorizationService.getUserRoles(identifier) :
                                        authorizationService.getUserRolesByUsername(identifier);

        return ResponseEntity.status(200).body(new AuthorizationAPIResponse("", new LinkedList<>(), foundRoles));
    }
}
