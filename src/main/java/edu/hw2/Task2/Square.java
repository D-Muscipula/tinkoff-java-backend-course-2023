package edu.hw2.Task2;

public final class Square extends Rectangle {

    public Square(int side) {
        super(side, side);
    }

    public Square() {

    }

    public Square setSide(int side) {
        return new Square(side);
    }

}
