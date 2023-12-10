package edu.project4;

import edu.project4.elements.FractalImage;
import edu.project4.elements.Rect;
import edu.project4.imageBuilder.ImageFormat;
import edu.project4.imageBuilder.ImageUtils;
import edu.project4.renders.SingleThreadRenderer;
import edu.project4.transformations.DiskTransformation;
import edu.project4.transformations.HandkerchiefTransformation;
import edu.project4.transformations.HeartTransformation;
import edu.project4.transformations.HyberbolicTransformation;
import edu.project4.transformations.LinearTransformation;
import edu.project4.transformations.SwirlTransformation;
import edu.project4.transformations.Transformation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SingleThreadRendererTest {
    @Test
    void renderTest() {
        List<Transformation> TRANSFORMATIONS = new ArrayList<>(List.of(
            new LinearTransformation(),
            new HeartTransformation(),
            new DiskTransformation(),
            new HyberbolicTransformation(),
            new SwirlTransformation(),
            new HandkerchiefTransformation()
        ));
        Path path = Path.of("src/test/java/edu/project4/dirForTest/test.png");
        SingleThreadRenderer singleThreadRenderer = new SingleThreadRenderer();
        FractalImage fractalImage = singleThreadRenderer.render(
            FractalImage.create(2048, 1080),
            new Rect(-1.5, -1.5, 3, 3),
            TRANSFORMATIONS,
            6,
            1,
            10,
            1000000
        );
        new LogGammaCorrector().process(fractalImage, 0.5);
        ImageUtils.save(
            fractalImage,
            path,
            ImageFormat.PNG
        );
        Assertions.assertTrue(Files.exists(path));
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
