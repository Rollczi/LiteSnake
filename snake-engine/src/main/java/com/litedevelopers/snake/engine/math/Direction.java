package com.litedevelopers.snake.engine.math;

public class Direction extends Position {

    public static final Direction UP = new Direction(0, 1);
    public static final Direction DOWN = new Direction(0, -1);
    public static final Direction LEFT = new Direction(-1, 0);
    public static final Direction RIGHT = new Direction(1, 0);

    public Direction(double x, double y) {
        super(x, y);
    }

    public Direction getOpposite() {
        return new Direction(-this.getX(), -this.getY());
    }

    public Direction subtract(double xy) {
        return subtract(xy, xy);
    }

    public Direction subtract(double x, double y) {
        return new Direction(this.x - x, this.y - y);
    }

    public Direction subtract(Position vector) {
        return subtract(vector.x, vector.y);
    }

}
