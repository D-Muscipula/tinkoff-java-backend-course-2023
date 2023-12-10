package edu.project4;

import edu.project4.elements.FractalImage;

@FunctionalInterface
public
interface ImageProcessor {
    void process(FractalImage image, double gamma);
}
