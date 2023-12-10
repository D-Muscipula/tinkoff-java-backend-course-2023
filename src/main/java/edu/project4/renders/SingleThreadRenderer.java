package edu.project4.renders;

import edu.project4.elements.FractalImage;
import edu.project4.elements.Rect;
import edu.project4.transformations.AffineTransformation;
import edu.project4.transformations.Transformation;
import java.util.List;

public class SingleThreadRenderer extends AbstractRenderer implements Renderer {
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
            renderFrame(countOfIterations, symmetry, image, world, variants, affineTransformations);
        }
        return image;
    }
}
