package edu.project4;

import edu.project4.elements.FractalImage;
import edu.project4.elements.Pixel;

public class LogGammaCorrector implements ImageProcessor {
    public void process(FractalImage image, double gamma) {
        double max = 0.0;
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                if (image.pixel(x, y) == null) {
                    continue;
                }
                if (image.pixel(x, y).getHitCount() != 0) {
                    image.pixel(x, y).setNormal(Math.log10(image.pixel(x, y).getHitCount()));
                    if (image.pixel(x, y).getNormal() > max) {
                        max = image.pixel(x, y).getNormal();
                    }
                }
            }
        }
        for (int y = 0; y < image.height(); y++) {
            for (int x = 0; x < image.width(); x++) {
                Pixel pixel = image.pixel(x, y);
                if (pixel == null || image.pixel(x, y) == null) {
                    continue;
                }
                pixel.setNormal(image.pixel(x, y).getNormal() / max);
                int r = ((int) (pixel.getR() * Math.pow(pixel.getNormal(), (1.0 / gamma))));
                int g = ((int) (pixel.getG() * Math.pow(pixel.getNormal(), (1.0 / gamma))));
                int b = ((int) (pixel.getB() * Math.pow(pixel.getNormal(), (1.0 / gamma))));
                pixel.setRGB(r, g, b);
            }
        }
    }

}

