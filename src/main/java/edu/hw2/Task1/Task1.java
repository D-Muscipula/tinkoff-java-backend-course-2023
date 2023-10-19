package edu.hw2.Task1;

public class Task1 {

    public sealed interface Expr {
        double evaluate();

        record Constant(double num) implements Expr {
            @Override
            public double evaluate() {
                return num;
            }
        }

        record Negate(Expr expr) implements Expr {
            @Override
            public double evaluate() {
                if (expr == null) {
                    return 0;
                }
                return -expr.evaluate();
            }
        }

        record Exponent(Expr expr, double pow) implements Expr {
            @Override
            public double evaluate() {
                if (expr == null) {
                    return 0;
                }
                return Math.pow(expr.evaluate(), pow);
            }
        }

        record Addition(Expr expr1, Expr expr2) implements Expr {
            @Override
            public double evaluate() {
                if (expr1 == null || expr2 == null) {
                    return 0;
                }
                return expr1.evaluate() + expr2.evaluate();
            }
        }

        record Multiplication(Expr expr1, Expr expr2) implements Expr {
            @Override
            public double evaluate() {
                if (expr1 == null || expr2 == null) {
                    return 0;
                }
                return expr1.evaluate() * expr2.evaluate();
            }
        }
    }
}
