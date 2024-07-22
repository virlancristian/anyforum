package net.anyforum.backend.controllers.images;

import net.anyforum.backend.constants.MiddlewareMessage;
import net.anyforum.backend.middleware.common.CommonMiddleware;
import net.anyforum.backend.middleware.images.ImageMiddleware;
import net.anyforum.backend.models.api.common.CommonAPIResponse;
import net.anyforum.backend.models.dto.image.ImageDTO;
import net.anyforum.backend.services.image.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    private CommonMiddleware commonMiddleware;
    private ImageMiddleware imageMiddleware;
    private ImageStorageService imageStorageService;

    @Autowired
    public ImageController(CommonMiddleware commonMiddleware, ImageMiddleware imageMiddleware, ImageStorageService imageStorageService) {
        this.commonMiddleware = commonMiddleware;
        this.imageMiddleware = imageMiddleware;
        this.imageStorageService = imageStorageService;
    }

    @CrossOrigin
    @PostMapping(value = "/avatar/user/{id}/upload", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<CommonAPIResponse> uploadImage(@PathVariable("id") String userID,
                                                         @RequestHeader("Authorization") String authorizationToken,
                                                         @RequestBody MultipartFile profilePicture) {
        MiddlewareMessage requestIntegrityMessage = commonMiddleware.verifyRequestIntegrity(userID, authorizationToken);
        MiddlewareMessage imageUploadRequestIntegrity = imageMiddleware.verifyProfilePictureUploadRequest(profilePicture);

        if(requestIntegrityMessage != MiddlewareMessage.OK) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonAPIResponse(requestIntegrityMessage.getMessage()));
        }

        if(imageUploadRequestIntegrity != MiddlewareMessage.OK) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CommonAPIResponse(imageUploadRequestIntegrity.getMessage()));
        }

        String storedImageName = imageStorageService
                                .saveProfilePictureImage(new ImageDTO().mapToBufferedImage(profilePicture),
                                                        userID,
                                                        profilePicture.getOriginalFilename());

        if(storedImageName.isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CommonAPIResponse("Internal server error."));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new CommonAPIResponse("Image saved successfully!", storedImageName));
    }

    @CrossOrigin
    @GetMapping(value = "/avatar/user/{id}", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity<Resource> getProfilePicture(@PathVariable("id") String userID,
                                                      @RequestParam("format") String imageFormat) {
        if(imageMiddleware.verifyGetProfilePictureRequest(imageFormat.toLowerCase()) != MiddlewareMessage.OK) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        ByteArrayResource foundImageBAR = imageStorageService.getProfilePicture(userID.concat(".".concat(imageFormat.toLowerCase())));

        if(foundImageBAR == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK).contentLength(foundImageBAR.contentLength()).body(foundImageBAR);
    }
}
