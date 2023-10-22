package edu.hw2;

import edu.hw2.Task1.Task1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static java.lang.Double.NaN;

public class Task1Test {
    @ParameterizedTest
    @ValueSource(doubles = {1.5, 2.73, 3, 0, -1})
    void constantTest(double d) {
        var constant = new Task1.Expr.Constant(d);
        Assertions.assertEquals(d, constant.evaluate());
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.5, 2.73, 3, 0, -1})
    void negateTest(double d) {
        var constant = new Task1.Expr.Constant(d);
        var negate = new Task1.Expr.Negate(constant);
        Assertions.assertEquals(-d, negate.evaluate());
        Assertions.assertEquals(0, new Task1.Expr.Negate(null).evaluate());
    }

    @ParameterizedTest
    @ValueSource(doubles = {1.5, 2.73, 3, 0, -1})
    void exponentTest(double d) {
        var constant = new Task1.Expr.Constant(d);
        for (int i = -2; i < 10; i++) {
            Assertions.assertEquals(Math.pow(d, i), new Task1.Expr.Exponent(constant, i).evaluate());
        }
        Assertions.assertEquals(0, new Task1.Expr.Exponent(null, 1).evaluate());
        Assertions.assertEquals(NaN, new Task1.Expr.Exponent(new Task1.Expr.Constant(-1), 1.4).evaluate());
    }

    @Test
    void additionTest() {
        double[] doubles = {1.5, 2.73, 3, 0, -1};
        for (int i = 0; i < doubles.length - 1; i++) {
            var constant1 = new Task1.Expr.Constant(doubles[i]);
            var constant2 = new Task1.Expr.Constant(doubles[i + 1]);
            var addition = new Task1.Expr.Addition(constant1, constant2);
            Assertions.assertEquals(doubles[i] + doubles[i + 1], addition.evaluate());
        }
        Assertions.assertEquals(1, new Task1.Expr.Addition(null, new Task1.Expr.Constant(1)).evaluate());
        Assertions.assertEquals(5, new Task1.Expr.Addition(new Task1.Expr.Constant(5), null).evaluate());
        Assertions.assertEquals(0, new Task1.Expr.Addition(null, null).evaluate());
    }

    @Test
    void multiplicationTest() {
        double[] doubles = {1.5, 2.73, 3, 0, -1};
        for (int i = 0; i < doubles.length - 1; i++) {
            var constant1 = new Task1.Expr.Constant(doubles[i]);
            var constant2 = new Task1.Expr.Constant(doubles[i + 1]);
            var multiplication = new Task1.Expr.Multiplication(constant1, constant2);
            Assertions.assertEquals(doubles[i] * doubles[i + 1], multiplication.evaluate());
        }
        Assertions.assertEquals(0, new Task1.Expr.Multiplication(null, new Task1.Expr.Constant(1)).evaluate());
    }

    @Test
    void allInAllTest() {
        var two = new Task1.Expr.Constant(2);
        var four = new Task1.Expr.Constant(4);
        var negOne = new Task1.Expr.Negate(new Task1.Expr.Constant(1));
        var sumTwoFour = new Task1.Expr.Addition(two, four);
        var mult = new Task1.Expr.Multiplication(sumTwoFour, negOne);
        var exp = new Task1.Expr.Exponent(mult, 2);
        var res = new Task1.Expr.Addition(exp, new Task1.Expr.Constant(1));
        Assertions.assertEquals((2 + 4) * -1 * (2 + 4) * -1 + 1, res.evaluate());
    }
}
