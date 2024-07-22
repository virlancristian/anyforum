package net.anyforum.backend.services.image;

import net.anyforum.backend.models.dto.image.ImageDTO;
import net.anyforum.backend.util.file.FileUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@Service
public class ImageStorageService {
    private static final String IMAGES_DIRECTORY = "images";
    private static final Logger logger = LogManager.getLogger();

    public ImageStorageService() {
        File imagesDirectory = new File(IMAGES_DIRECTORY);
        File profilePicturesDirectory = new File(IMAGES_DIRECTORY.concat("/avatars"));

        if(!imagesDirectory.exists()) {
            imagesDirectory.mkdir();
        }

        if(!profilePicturesDirectory.exists()) {
            profilePicturesDirectory.mkdir();
        }
    }

    public String saveProfilePictureImage(BufferedImage image, String userID, String receivedImageName) {
        String imageExtension = FileUtil.getFileExtension(receivedImageName);
        String storedImageName = new String(userID.concat(".".concat(imageExtension)));
        File imageFile = new File(IMAGES_DIRECTORY.concat("/avatars/".concat(storedImageName)));

        try {
            ImageIO.write(image, imageExtension, imageFile);
        } catch(IOException error) {
            logger.error("Error in ImageStorageService::saveProfilePictureImage - failed to save image: " + error.getMessage());
            return "";
        }

        return storedImageName;
    }

    public ByteArrayResource getProfilePicture(String imageName) {
        try {
            File imageFile = new File(IMAGES_DIRECTORY.concat("/avatars/".concat(imageName)));
            BufferedImage foundImage = ImageIO.read(imageFile);

            return new ImageDTO().mapToByteArrayResource(foundImage, FileUtil.getFileExtension(imageName));
        } catch (IOException error) {
            logger.error("Error in ImageStorageService::getProfilePicture - failed to get image: " + error.getMessage());
            return null;
        }
    }
}
