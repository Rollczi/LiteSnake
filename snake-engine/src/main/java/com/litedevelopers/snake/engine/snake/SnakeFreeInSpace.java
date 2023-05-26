package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.math.RotatedBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class SnakeFreeInSpace implements Snake {

    private final String name;
    private final double partSize;

    private RotatedBox head;
    private final List<RotatedBox> bodyParts = new ArrayList<>();
    private double boost = 0.0;

    SnakeFreeInSpace(String name, Position position, double headSize, int startLength) {
        this.name = name;
        this.partSize = headSize;
        this.head = new RotatedBox(position.subtract(partSize), position.add(partSize), new Position(0, 1));
        this.setLength(startLength);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Position move(double velocity, Position direction) {
        if (direction.isZero() || direction.isNaN()) {
            direction = new Position(1, 1);
        }

        double angle = direction.normalize().angle(head.direction());

        if (angle > 10) {

        }

        double size = partSize * 2;
        Position move = direction
                .normalize()
                .multiple(velocity, velocity);

        if (this.bodyParts.size() > 0 && this.head.center().distance(bodyParts.get(0).center()) > size) {
            Position directionToBody = this.bodyParts.get(0).center().subtract(this.head.center());

            if (directionToBody.isZero()) {
                directionToBody = move;
            }

            this.bodyParts.add(0, head.move(directionToBody.setLength(size / 3)));
            this.bodyParts.remove(this.bodyParts.size() - 1);
        }

        this.head = this.head.move(move).direction(direction.normalize());

        return this.head.center();
    }

    @Override
    public RotatedBox getHead() {
        return this.head;
    }

    @Override
    public Position getHeadPosition() {
        return this.head.center();
    }

    @Override
    public Position getDirection() {
        return this.head.direction();
    }

    @Override
    public List<RotatedBox> getBodyParts() {
        return this.bodyParts;
    }

    @Override
    public void setLength(int length) {
        int size = this.bodyParts.size();

        if (size == length) {
            return;
        }

        if (size > length) {
            for (int i = size - length; i < size; i++) {
                this.bodyParts.remove(i);
            }

            return;
        }

        for (int i = 0; i < length - size; i++) {
            RotatedBox last = this.getLast();
            Position move = last.direction().normalize().multiple(this.partSize * - 2);

            this.bodyParts.add(last.move(move));
        }
    }

    @Override
    public double getBoost() {
        return boost;
    }

    @Override
    public void setBoost(double boost) {
        this.boost = boost;
    }

    @Override
    public void addBoost(double boost) {
        this.boost += boost;
    }

    public RotatedBox getLast() {
        if (bodyParts.isEmpty()) {
            return this.head;
        }

        return bodyParts.get(bodyParts.size() - 1);
    }

    @Override
    public int getLength() {
        return this.bodyParts.size();
    }

    @Override
    public double getPartSize() {
        return this.partSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof SnakeFreeInSpace)) {
            return false;
        }

        SnakeFreeInSpace that = (SnakeFreeInSpace) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
