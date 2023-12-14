package edu.project4.elements;

import java.awt.Color;

public class Pixel {
    private int r;
    private int g;
    private int b;
    private int hitCount;
    private double normal;

    public Pixel() {
        this.r = 0;
        this.g = 0;
        this.b = 0;
        this.hitCount = 0;
        this.normal = 0;
    }

    public Pixel(int r, int g, int b, int hitCount, double normal) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.hitCount = hitCount;
        this.normal = normal;
    }

    public void setRGB(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void decreaseHitCount(Color color) {
        if (hitCount == 0) {
            setRGB(color.getRed(), color.getGreen(), color.getBlue());
        } else {
            r = (r + color.getRed()) / 2;
            g = (g + color.getGreen()) / 2;
            b = (b + color.getBlue()) / 2;
        }
        hitCount++;
    }

    public static Pixel pointToPixel(FractalImage image, Point point, Rect world) {
        if (world.notContains(point)) {
            return null;
        }
        return image.pixel(
            (int) ((point.x() - world.x()) / world.width() * image.width()),
            (int) ((point.y() - world.y()) / world.height() * image.height())
        );
    }

    public static Point rotate(Rect world, Point point, double theta) {
        double centerX = world.x() + world.width() / 2;
        double centerY = world.y() + world.height() / 2;

        double rotatedX = (point.x() - centerX) * Math.cos(theta) - (point.y() - centerY) * Math.sin(theta);
        double rotatedY = (point.x() - centerX) * Math.sin(theta) + (point.y() - centerY) * Math.cos(theta);

        return new Point(rotatedX + centerX, rotatedY + centerY);
    }

    public int getHitCount() {
        return hitCount;
    }

    public double getNormal() {
        return normal;
    }

    public void setNormal(double normal) {
        this.normal = normal;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }
}
