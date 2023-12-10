package edu.project4.transformations;

import edu.project4.elements.Point;

public class HandkerchiefTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double x = point.x();
        double y = point.y();
        double r = Math.sqrt(x * x + y * y);
        double fi = Math.atan(x / y);
        return new Point(r * Math.sin(fi + r), r * Math.cos(fi - r));
    }
}
