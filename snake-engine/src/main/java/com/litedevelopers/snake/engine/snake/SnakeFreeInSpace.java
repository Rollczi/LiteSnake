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


    public SnakeFreeInSpace(String name, Position position, double headSize) {
        this.name = name;
        this.partSize = headSize;
        this.head = new RotatedBox(position.subtract(partSize), position.add(partSize), new Position(0, 0));
    }

    SnakeFreeInSpace(String name, double headSize) {
        this(name, new Position(0, 0), headSize);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Position move(double velocity, Position direction) {
        if (this.bodyParts.size() > 0) {
            this.bodyParts.add(0, this.head);
            this.bodyParts.remove(this.bodyParts.size() - 1);
        }


        this.head = this.head.move(direction
                .normalize()
                .multiple(velocity, velocity))
                .direction(direction.normalize());
        System.out.println(this.head.direction());
        System.out.println(this.head.rotation());

        return this.head.center();
    }

    @Override
    public RotatedBox getHead() {
        return this.head;
    }

    @Override
    public Position getPosition() {
        return this.head.center();
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
            this.bodyParts.add(this.getLast());
        }
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
