package net.anyforum.backend.controllers.auhtorization;

import net.anyforum.backend.constants.AuthConstants;
import net.anyforum.backend.middleware.session.SessionMiddleware;
import net.anyforum.backend.models.api.authorization.AuthorizationAPIResponse;
import net.anyforum.backend.models.authorization.PermissionDbEntity;
import net.anyforum.backend.models.authorization.RoleDbEntity;
import net.anyforum.backend.services.authorization.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/authorization")
public class AuthorizationController {
    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private SessionMiddleware sessionMiddleware;

    @CrossOrigin
    @GetMapping("/roles/user/{identifier}")
    public ResponseEntity<List<RoleDbEntity>> getUserRoles(@PathVariable("identifier") String identifier) {
        List<RoleDbEntity> foundRoles = identifier.matches(AuthConstants.ID_REGEX) ?
                                        authorizationService.getUserRoles(identifier) :
                                        authorizationService.getUserRolesByUsername(identifier);

        return ResponseEntity.status(HttpStatus.OK).body(foundRoles);
    }

    @CrossOrigin
    @GetMapping("/permissions/user/{id}")
    public ResponseEntity<List<PermissionDbEntity>> getUserPerms(@PathVariable("id") String userID) {
        return ResponseEntity.status(HttpStatus.OK).body(authorizationService.getUserPerms(userID));
    }

    @CrossOrigin
    @GetMapping("/permissions/all")
    public ResponseEntity<List<PermissionDbEntity>> getAllPermissions() {
        List<PermissionDbEntity> allPermissions = authorizationService.getAllPermissions();

        return ResponseEntity.status(HttpStatus.OK).body(allPermissions);
    }
}
