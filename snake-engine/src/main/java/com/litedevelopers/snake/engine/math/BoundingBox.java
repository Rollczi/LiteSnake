package com.litedevelopers.snake.engine.math;

public class BoundingBox {

    protected Position min;
    protected Position max;

    public BoundingBox(Position min, Position max) {
        this.min = min;
        this.max = max;
    }

    public void setPosition(Position position) {
        Vector difference = this.min.getDifference(this.max);

        this.min = position;

        this.max = position.add(difference);
    }

    public Position getMinPosition() {
        return min;
    }

    public Position getMaxPosition() {
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


    @Override
    public String toString() {
        return "BoundingBox{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
