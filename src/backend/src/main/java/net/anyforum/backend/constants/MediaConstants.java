package net.anyforum.backend.constants;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MediaConstants {
    static final String[] ACCEPTED_IMAGE_FORMATS_ARRAY = new String[]{"png", "jpg", "jpeg", "gif"};
    public static final List<String> ACCEPTED_IMAGE_FORMATS = new LinkedList<>(Arrays.stream(ACCEPTED_IMAGE_FORMATS_ARRAY).toList());
}
