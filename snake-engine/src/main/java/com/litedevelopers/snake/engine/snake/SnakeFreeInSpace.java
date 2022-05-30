package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.Direction;
import com.litedevelopers.snake.engine.math.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

class SnakeFreeInSpace implements Snake {

    private final String name;
    private final double partSize;

    private Position head;
    private final List<Position> bodyParts = new ArrayList<>();


    public SnakeFreeInSpace(String name, Position position, double headSize) {
        this.name = name;
        this.partSize = headSize;
        this.head = position;
    }

    SnakeFreeInSpace(String name, double headSize) {
        this.name = name;
        this.partSize = headSize;
        this.head = new Position(0,0);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Position move(double velocity, Direction direction) {
        if (this.bodyParts.size() > 0) {
            this.bodyParts.add(0, this.head);
            this.bodyParts.remove(this.bodyParts.size() - 1);
        }

        this.head = direction
                .normalize()
                .multiple(velocity, velocity)
                .add(this.head);

        return this.head;
    }

    @Override
    public Position moveWithApple(double velocity, Direction direction) {
        this.bodyParts.add(0, this.head);

        this.head = direction
                .normalize()
                .multiple(velocity, velocity)
                .add(this.head);

        return this.head;
    }

    @Override
    public BodyHead getHead() {
        return new BodyHead(this.head.subtract(partSize), this.head.add(partSize));
    }

    @Override
    public List<BodyPart> getBodyParts() {
        return this.bodyParts.stream()
                .map(position -> new BodyPart(position.subtract(partSize), position.add(partSize)))
                .collect(Collectors.toList());
    }

    @Override
    public int getLength() {
        return this.bodyParts.size() + 1;
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
