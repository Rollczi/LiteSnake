package com.litedevelopers.snake.engine.math;

public class Direction extends Vector{

    public static final Direction UP = new Direction(0, 1);
    public static final Direction DOWN = new Direction(0, -1);
    public static final Direction LEFT = new Direction(-1, 0);
    public static final Direction RIGHT = new Direction(1, 0);

    public Direction getOpposite() {
        return new Direction(-super.getX(), -super.getY());
    }

    public Direction(double x, double y) {
        super(x, y);
    }
}
