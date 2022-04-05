package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.Direction;
import com.litedevelopers.snake.engine.math.Position;

import java.util.List;

public interface Snake {

    String getName();

    void move(double velocity, Direction direction);

    void moveWithApple(double velocity, Direction direction);

    BodyHead getHead();

    List<BodyPart> getBodyParts();

    int getLength();
}
