package edu.project4.renders;

import edu.project4.elements.FractalImage;
import edu.project4.elements.Rect;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.Transformation;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultipleThreadRenderer extends AbstractRenderer implements Renderer {
    private static final int THREADS_AMOUNT = 4;
    private final ExecutorService executorService;

    public MultipleThreadRenderer() {
        executorService = Executors.newFixedThreadPool(THREADS_AMOUNT);
    }

    @Override
    public FractalImage render(
        FractalImage image,
        Rect world,
        List<Transformation> variants,
        int affineAmount,
        int symmetry,
        int countOfSamples,
        int countOfIterations
    ) {
        List<AffineTransformation> affineTransformations = AffineTransformation.generateAffineList(affineAmount);
        for (int i = 0; i < countOfSamples; i++) {
            executorService.execute(
                () -> renderFrame(countOfIterations, symmetry, image, world, variants, affineTransformations)
            );
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return image;
    }
}
