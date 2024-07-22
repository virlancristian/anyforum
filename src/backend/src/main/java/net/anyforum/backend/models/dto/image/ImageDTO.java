package net.anyforum.backend.models.dto.image;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ImageDTO {
    private static final Logger logger = LogManager.getLogger();

    public BufferedImage mapToBufferedImage(MultipartFile receivedImage) {
        BufferedImage convertedImage;

        try {
            byte[] receivedImageBytes = receivedImage.getBytes();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(receivedImageBytes);

            convertedImage = ImageIO.read(byteArrayInputStream);
        } catch (IOException error) {
            logger.error("Error while trying to convert image from MultipartFile to BufferedImage: " + error.getMessage()) ;
            return null;
        }

        return convertedImage;
    }

    public ByteArrayResource mapToByteArrayResource(BufferedImage image, String imageFormat) {
        ByteArrayOutputStream imageBAOS = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, imageFormat, imageBAOS);
        } catch(IOException error) {
            logger.error("Error while trying to convert image from BufferedImage to ByteArrayResource: " + error.getMessage());
            return null;
        }

        return new ByteArrayResource(imageBAOS.toByteArray());
    }
}
