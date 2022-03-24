package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.Position;

import java.util.List;

public class SnakeFreeInSpace implements Snake {

    private final String name;

    SnakeFreeInSpace(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void move(Position position) {

    }

    @Override
    public void moveWithApple(Position position) {

    }

    @Override
    public double getLength() {
        return 0;
    }

    @Override
    public void setHeadPosition(Position position) {

    }

    @Override
    public Position getHeadPosition() {
        return null;
    }

    @Override
    public List<Position> getPosition() {
        return null;
    }


}