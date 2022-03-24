package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.Position;

import java.util.List;

public interface Snake {

    String getName();

    void move(Position position);

    void moveWithApple(Position position);

    double getLength();

    void setHeadPosition(Position position);

    Position getHeadPosition();

    List<Position> getPosition();
}
