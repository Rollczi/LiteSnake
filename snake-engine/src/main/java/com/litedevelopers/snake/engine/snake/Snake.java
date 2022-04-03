package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.Direction;
import com.litedevelopers.snake.engine.math.Position;

import java.util.List;

public interface Snake {

    String getName();

    void move(Direction direction);

    void move();

    void moveWithApple(Direction direction);

    void moveWithApple();

    void setDirection(Direction direction);

    Direction getDirection();

    BodyHead getHead();

    List<BodyPart> getBodyParts();

    int getLength();
}
