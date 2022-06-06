package com.litedevelopers.snake.engine.math;

import java.util.Objects;

public class Position {

    public static final Position ZERO = new Position(0, 0);
    public static final Position ONE = new Position(1, 1);

    protected final double x;
    protected final double y;

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

    public Position getDifference(Position position) {
        double posX = position.getX();
        double posY = position.getY();

        double vectorX = Math.abs(this.x - posX);
        double vectorY = Math.abs(this.y - posY);

        return new Position(vectorX, vectorY);
    }

    public Position add(double xy) {
        return this.add(xy, xy);
    }

    public Position add(Position vector) {
        return this.add(vector.x, vector.y);
    }

    public Position add(double x, double y) {
        return new Position(this.x + x, this.y + y);
    }

    public Position subtract(double xy) {
        return this.subtract(xy, xy);
    }

    public Position subtract(Position vector) {
        return this.subtract(vector.x, vector.y);
    }

    public Position subtract(double x, double y) {
        return new Position(this.x - x, this.y - y);
    }

    public Position multiple(double xy) {
        return this.multiple(xy, xy);
    }

    public Position multiple(Position vector) {
        return this.multiple(vector.x, vector.y);
    }

    public Position multiple(double x, double y) {
        return new Position(this.x * x, this.y * y);
    }

    public Position device(double xy) {
        return this.device(xy, xy);
    }

    public Position device(Position vector) {
        return this.device(vector.x, vector.y);
    }

    public Position device(double x, double y) {
        return new Position(this.x / x, this.y / y);
    }

    public Position normalize() {
        double length = getLength();
        return new Position(x / length, y / length);
    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    public Position setLength(double len) {
        return this.device(this.getLength()).multiple(len);
    }

    public Position setLength2(double len2) {
        double oldLen2 = len2();
        return (oldLen2 == 0 || oldLen2 == len2) ? this : multiple(Math.sqrt(len2 / oldLen2));
    }

    public double len2() {
        return x * x + y * y;
    }

    public double distance(double x, double y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    public double distance(Position position) {
        return this.distance(position.getX(), position.getY());
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return Double.compare(position.x, x) == 0 && Double.compare(position.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public boolean isZero() {
        return x == 0 && y == 0;
    }

    public boolean isNaN() {
        return Double.isNaN(x) || Double.isNaN(y);
    }

}