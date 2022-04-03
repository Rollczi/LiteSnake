package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.Direction;
import com.litedevelopers.snake.engine.math.Position;

import java.util.ArrayList;
import java.util.List;

public class SnakeGrid implements Snake{

    private final String name;
    private BodyHead head;
    private final List<BodyPart> bodyParts = new ArrayList<>();
    private final double partSize;
    private Direction currentDirection;
    private boolean isAlive;

    public SnakeGrid(String name, Position position, double headSize) {
        this.name = name;
        this.partSize = headSize;
        this.head = new BodyHead(position, headSize);
        this.currentDirection = Direction.UP;
    }

    public SnakeGrid(String name, double headSize) {
        this.name = name;
        this.partSize = headSize;
        this.head = new BodyHead(new Position(0,0), headSize);
        this.currentDirection = Direction.UP;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void move(Direction direction) {
        this.currentDirection = direction;

        if (this.bodyParts.size() > 0) {
            this.bodyParts.add(0, new BodyPart(this.head.getCenter(), this.partSize));
            this.bodyParts.remove(this.bodyParts.size() - 1);
        }

        this.head = new BodyHead(this.head.getCenter().add(direction), this.partSize);
    }

    @Override
    public void move() {
        this.move(this.currentDirection);
    }

    @Override
    public void moveWithApple(Direction direction) {
        this.currentDirection = direction;

        this.bodyParts.add(0, new BodyPart(this.head.getCenter(), this.partSize));

        this.head = new BodyHead(this.head.getCenter().add(direction), this.partSize);
    }

    @Override
    public void moveWithApple() {
        this.moveWithApple(this.currentDirection);
    }

    @Override
    public void setDirection(Direction direction) {
        this.currentDirection = direction;
    }

    @Override
    public Direction getDirection() {
        return currentDirection;
    }

    @Override
    public BodyHead getHead() {
        return head;
    }

    @Override
    public List<BodyPart> getBodyParts() {
        return bodyParts;
    }

    @Override
    public int getLength() {
        return this.bodyParts.size() + 1;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void kill() {
        isAlive = false;
    }

    @Override
    public String toString() {
        return "SnakeGrid{" +
                "name='" + name + '\'' +
                ", bodyParts=" + bodyParts +
                ", partSize=" + partSize +
                ", headX=" + head.getCenter().getX() +
                ", headY=" + head.getCenter().getY() +
                '}';
    }
}
