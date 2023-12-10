package edu.project4;

import edu.project4.elements.FractalImage;
import edu.project4.elements.Pixel;
import edu.project4.imageBuilder.ImageFormat;
import edu.project4.imageBuilder.ImageUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ImageBuilderTest {
    @Test
    void saveTest() {
        FractalImage fractalImage = FractalImage.create(300, 300);
        Pixel[] data = fractalImage.data();
        Path path = Path.of("src/test/java/edu/project4/dirForTest/test.png");
        for (int i = 0; i < fractalImage.height() * fractalImage.width(); i++) {
            Pixel pixel = data[i];
            if (i % 2 == 0) {
                pixel.setRGB(i % 255, (i * 2) % 255, (i * 3) % 255);
            }
        }
        ImageUtils.save(fractalImage, path, ImageFormat.PNG);
        Assertions.assertTrue(Files.exists(path));
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
