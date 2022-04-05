package com.litedevelopers.snake.engine.math;

public class BoundingBox {

    protected final Position min;
    protected final Position max;

    public BoundingBox(Position min, Position max) {
        this.min = min;
        this.max = max;
    }

    public BoundingBox move(Position position) {
        Position difference = this.min.subtract(this.max);

        return new BoundingBox(position, position.add(difference));
    }

    public Position getMinPosition() {
        return min;
    }

    public Position getMaxPosition() {
        return max;
    }

    public Position getCenter() {
        return new Position((min.getX() + max.getX()) / 2, (min.getY() + max.getY()) / 2);
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
