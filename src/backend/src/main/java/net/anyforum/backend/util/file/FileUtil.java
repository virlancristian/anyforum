package net.anyforum.backend.util.file;

import java.awt.image.BufferedImage;
import java.io.File;

public class FileUtil {
    public static String getFileExtension(String filename) {
        String[] fullFilenameSplit = filename.split("\\.");

        return fullFilenameSplit[fullFilenameSplit.length - 1];
    }
}
