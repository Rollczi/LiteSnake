package com.litedevelopers.snake.engine.math;

public class Position {

    private final double x;
    private final double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Position add(double x, double y) {
        return new Position(this.x + x, this.y + y);
    }

}