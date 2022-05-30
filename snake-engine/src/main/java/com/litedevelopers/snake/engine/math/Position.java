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

    public int getIntX() {
        return (int) x;
    }

    public int getIntY() {
        return (int) y;
    }

    public Vector getDifference(Position position) {
        double posX = position.getX();
        double posY = position.getY();

        double vectorX = Math.abs(this.x - posX);
        double vectorY = Math.abs(this.y - posY);

        return new Vector(vectorX, vectorY);
    }

    public Position add(double xy) {
        return add(xy, xy);
    }

    public Position add(double x, double y) {
        return new Position(this.x + x, this.y + y);
    }

    public Position add(Position vector) {
        return new Position(
                this.x + vector.getX(),
                this.y + vector.getY());
    }

    public Position subtract(double xy) {
        return subtract(xy, xy);
    }

    public Position subtract(double x, double y) {
        return new Position(this.x - x, this.y - y);
    }

    public Position subtract(Position vector) {
        return subtract(vector.x, vector.y);
    }

    public Position multiple(double x, double y) {
        return new Position(this.x * x, this.y * y);
    }

    public Position multiple(Position vector) {
        return multiple(vector.x, vector.y);
    }

    public Position normalize() {
        double length = getLength();
        return new Position(x / length, y / length);
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}