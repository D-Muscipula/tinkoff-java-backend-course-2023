package edu.project4;

import edu.project4.elements.FractalImage;
import edu.project4.elements.Rect;
import edu.project4.imageBuilder.ImageFormat;
import edu.project4.imageBuilder.ImageUtils;
import edu.project4.renders.MultipleThreadRenderer;
import edu.project4.transformations.DiskTransformation;
import edu.project4.transformations.HandkerchiefTransformation;
import edu.project4.transformations.HeartTransformation;
import edu.project4.transformations.HyberbolicTransformation;
import edu.project4.transformations.LinearTransformation;
import edu.project4.transformations.SwirlTransformation;
import edu.project4.transformations.Transformation;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class Main {
    @SuppressWarnings("checkstyle:MagicNumber") public static void main(String[] args) {
        List<Transformation> transformations = new ArrayList<>(List.of(
            new LinearTransformation(),
            new HeartTransformation(),
            new DiskTransformation(),
            new HyberbolicTransformation(),
            new SwirlTransformation(),
            new HandkerchiefTransformation()
        ));
        Path path = Path.of("src/main/java/edu/project4/images/picture.png");
        MultipleThreadRenderer singleThreadRenderer = new MultipleThreadRenderer();
        FractalImage fractalImage = singleThreadRenderer.render(
            FractalImage.create(2048, 1080),
            new Rect(-1.5, -1.5, 3, 3),
            transformations,
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
    }

private Main() {
}
}
