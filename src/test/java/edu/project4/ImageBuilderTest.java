package edu.project4;

import edu.project4.elements.FractalImage;
import edu.project4.elements.Pixel;
import edu.project4.imageBuilder.ImageFormat;
import edu.project4.imageBuilder.ImageUtils;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImageBuilderTest {
    @Test
    void saveTest() {
        FractalImage fractalImage = FractalImage.create(50, 50);
        Pixel[] data = fractalImage.data();
        for (int i = 0; i < fractalImage.height() * fractalImage.width(); i++) {
            Pixel pixel = data[i];
            if (i % 2 == 0) {
                pixel.setRGB(i % 255, (i * 2) % 255, (i * 3) % 255);
            }
        }

        try {
            Path path = Path.of("src/test/java/edu/project4/dirForTest/test.png");
            ImageUtils.save(fractalImage, path, ImageFormat.PNG);
            Assertions.assertTrue(Files.exists(path));
            Files.deleteIfExists(path);
        } catch (Exception ignored) {
        }
    }

}
