package com.litedevelopers.snake.engine.math;

public class BoundingBox {

    protected final Position min;
    protected final Position max;

    public BoundingBox(Position min, Position max) {
        this.min = min;
        this.max = max;
    }

    public Position getMin() {
        return min;
    }

    public Position getMax() {
        return max;
    }

    public boolean contains(Position position) {
        if (position.getX() < this.min.getX() || position.getX() > this.max.getX()) {
            return false;
        }

        if (position.getY() < this.min.getY() || position.getY() > this.max.getY()) {
            return false;
        }

        return true;
    }

}
