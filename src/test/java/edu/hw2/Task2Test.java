package edu.hw2;

import edu.hw2.Task2.Rectangle;
import edu.hw2.Task2.Square;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class Task2Test {
    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        Rectangle rec = rect.setWidth(20);
        rec = rec.setHeight(10);
        Assertions.assertEquals(200.0, rec.area());
    }

    @Test
    void squareTest() {
        Square sq = new Square(12);
        Assertions.assertEquals(144, sq.area());
        sq = sq.setSide(15);
        Assertions.assertEquals(225, sq.area());
    }
}
