package net.anyforum.backend.middleware.images;

import net.anyforum.backend.constants.MediaConstants;
import net.anyforum.backend.constants.MiddlewareMessage;
import net.anyforum.backend.services.session.SessionHelperService;
import net.anyforum.backend.util.file.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageMiddleware {
    public MiddlewareMessage verifyProfilePictureUploadRequest(MultipartFile image) {
        if(image == null) {
            return MiddlewareMessage.MISSING_REQUEST_BODY_PARAMETERS;
        }

        if(!MediaConstants.ACCEPTED_IMAGE_FORMATS.contains(FileUtil.getFileExtension(image.getOriginalFilename()))) {
            return MiddlewareMessage.INVALID_IMAGE_FORMAT;
        }

        return MiddlewareMessage.OK;
    }

    public MiddlewareMessage verifyGetProfilePictureRequest(String imageFormat) {
        return MediaConstants.ACCEPTED_IMAGE_FORMATS.contains(imageFormat) ? MiddlewareMessage.OK : MiddlewareMessage.INVALID_IMAGE_FORMAT;
    }
}
