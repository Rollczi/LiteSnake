package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.Position;

import java.util.ArrayList;
import java.util.List;

public class SnakeGrid implements Snake{

    private final String name;
    private BodyHead head;
    private final List<BodyPart> bodyParts = new ArrayList<>();
    private final double partSize;

    public SnakeGrid(String name, double headSize) {
        this.name = name;
        this.partSize = headSize;
        this.head = new BodyHead(
                new Position(0,0),
                new Position(headSize, headSize));
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void grow(Position position) {
        this.bodyParts.add(0, new BodyPart(position, position.add(partSize)));
    }

    @Override
    public void move(Position position) {
        this.moveWithApple(position);
        this.bodyParts.remove(this.bodyParts.size() - 1);
    }

    @Override
    public void moveWithApple(Position position) {
        Position previousHeadPosition = this.head.getMinPosition();

        this.grow(previousHeadPosition);
        this.head.setPosition(position);
    }

    @Override
    public double getLength() {
        this.head = this.head.move(position);
    }

    @Override
    public int getLength() {
        return this.bodyParts.size() + 1;
    }

    @Override
    public void setHeadPosition(Position position) {
        this.head.setPosition(position);
        this.head = this.head.move(position);
    }

    @Override
    public Position getHeadPosition() {
        return this.head.getMinPosition();
    }

    @Override
    public List<Position> getPosition() {
        return null;
    }

    @Override
    public String toString() {
        return "SnakeGrid{" +
                "name='" + name + '\'' +
                ", bodyParts=" + bodyParts +
                ", partSize=" + partSize +
                ", headX=" + getHeadPosition().getX() +
                ", headY=" + getHeadPosition().getY() +
                '}';
    }
}
