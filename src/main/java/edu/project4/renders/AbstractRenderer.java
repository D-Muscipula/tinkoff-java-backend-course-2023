package edu.project4.renders;

import edu.project4.elements.FractalImage;
import edu.project4.elements.Pixel;
import edu.project4.elements.Point;
import edu.project4.elements.Rect;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.Transformation;
import java.awt.Color;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import static edu.project4.elements.Pixel.rotate;

public abstract class AbstractRenderer {
    private static final int FIRST_ITERATIONS = 20;

    protected void renderFrame(
        int countOfIterations,
        int symmetry,
        FractalImage image,
        Rect world,
        List<Transformation> variants,
        List<AffineTransformation> affineTransformations
    ) {
        Point point = new Point(
            ThreadLocalRandom.current().nextDouble(world.x(), world.x() + world.width()),
            ThreadLocalRandom.current().nextDouble(world.y(), world.y() + world.height())
        );
        for (int step = -FIRST_ITERATIONS; step < countOfIterations; step++) {
            AffineTransformation affineTransformation =
                affineTransformations.get(ThreadLocalRandom.current().nextInt(affineTransformations.size()));
            Transformation variation = variants.get(ThreadLocalRandom.current().nextInt(affineTransformations.size()));
            point = affineTransformation.apply(point);
            point = variation.apply(point);
            if (step > 0) {
                double theta = 0.0;
                for (int s = 0; s < symmetry; theta += Math.PI * 2 / symmetry, s++) {
                    Point rotatedPoint = rotate(world, point, theta);
                    if (world.notContains(rotatedPoint)) {
                        continue;
                    }
                    Pixel pixel = Pixel.pointToPixel(image, rotatedPoint, world);
                    if (pixel == null) {
                        continue;
                    }
                    synchronized (pixel) {
                        Color color = affineTransformation.color();
                        pixel.decreaseHitCount(color);
                    }
                }

            }
        }
    }
}
