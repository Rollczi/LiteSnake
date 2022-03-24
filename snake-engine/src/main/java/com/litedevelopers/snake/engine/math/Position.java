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

    public Vector getDifference(Position position) {
        double posX = position.getX();
        double posY = position.getY();

        double vectorX = Math.abs(this.x - posX);
        double vectorY = Math.abs(this.y - posY);

        return new Vector(vectorX, vectorY);
    }

    public Position add(double x) {
        return add(x, x);
    }

    public Position add(double x, double y) {
        return new Position(this.x + x, this.y + y);
    }

    public Position add(Vector vector) {
        return new Position(
                this.x + vector.getX(),
                this.y + vector.getY());
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}