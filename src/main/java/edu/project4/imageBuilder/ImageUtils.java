package edu.project4.imageBuilder;

import edu.project4.elements.FractalImage;
import edu.project4.elements.Pixel;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public final class ImageUtils {
    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename, ImageFormat format) {
        BufferedImage buildingImage = new BufferedImage(image.width(), image.height(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                Color color;
                if (pixel != null) {
                    color = new Color(pixel.getR(), pixel.getG(), pixel.getB());
                    buildingImage.setRGB(x, y, color.getRGB());
                }

            }
        }
        try {
            ImageIO.write(buildingImage, format.name(), filename.toFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
