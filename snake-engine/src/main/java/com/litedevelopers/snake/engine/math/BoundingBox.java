package com.litedevelopers.snake.engine.math;

import java.util.HashMap;
import java.util.Map;

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

    public Position getMin() {
        return min;
    }

    public Position getMax() {
        return max;
    }

    public double width() {
        return this.max.getX() - this.min.getX();
    }

    public double height() {
        return this.max.getY() - this.min.getY();
    }

    public Position center() {
        return new Position((min.getX() + max.getX()) / 2, (min.getY() + max.getY()) / 2);
    }

    public boolean contains(BoundingBox boundingBox) {
        return this.contains(boundingBox.min) || this.contains(boundingBox.max) || boundingBox.contains(this.min) || boundingBox.contains(this.max);
    }

    public boolean containsAll(BoundingBox boundingBox) {
        return this.contains(boundingBox.min) && this.contains(boundingBox.max);
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

    public Position nearestPoint(Position pos) {
        Map<Double, Position> pointsByDistance = new HashMap<>();

        Position minX = new Position(min.getX(), pos.getY());
        pointsByDistance.put(minX.distance(pos), minX);

        Position maxX = new Position(max.getX(), pos.getY());
        pointsByDistance.put(maxX.distance(pos), maxX);

        Position minY = new Position(pos.getX(), min.getY());
        pointsByDistance.put(minY.distance(pos), minY);

        Position maxY = new Position(pos.getX(), max.getY());
        pointsByDistance.put(maxY.distance(pos), maxY);


        double last = Double.MAX_VALUE;
        Position lastPoint = Position.ZERO;

        for (Map.Entry<Double, Position> entry : pointsByDistance.entrySet()) {
            if (entry.getKey() < last) {
                last = entry.getKey();
                lastPoint = entry.getValue();
            }
        }

        return lastPoint;
    }

    @Override
    public String toString() {
        return "BoundingBox{" +
                "min=" + min +
                ", max=" + max +
                '}';
    }
}
