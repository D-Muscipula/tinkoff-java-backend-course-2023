package edu.project4.renders;

import edu.project4.elements.FractalImage;
import edu.project4.elements.Rect;
import edu.project4.transformations.Transformation;
import java.util.List;

public interface Renderer {
    FractalImage render(
        FractalImage image,
        Rect world,
        List<Transformation> variants,
        int affineAmount,
        int symmetry,
        int countOfSamples,
        int countOfIterations
    );
}
