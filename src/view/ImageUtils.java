/*
 */
package view;

import java.net.URL;
import javafx.scene.image.Image;

/**
 *
 * @author iblecher
 */
public class ImageUtils {

    private static final String RESOURCES_DIR = "/resources/";
    private static final String IMAGE_EXTENSION = ".jpg";

    public static Image getImage(String filename) {
        if (filename == null || filename.isEmpty()) {
            return null;
        }

        if (!filename.endsWith(IMAGE_EXTENSION)) {
            filename = filename + IMAGE_EXTENSION;
        }

        return new Image(ImageUtils.class.getResourceAsStream(RESOURCES_DIR + filename));
    }

    public static void main(String[] args) {
        URL url = ImageUtils.class.getResource(RESOURCES_DIR + "computer.jpg");
        System.out.println(url != null ? url.toString() : "null");
    }
}
