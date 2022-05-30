package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.Direction;
import com.litedevelopers.snake.engine.math.Position;

import java.util.List;

public interface Snake {

    String getName();

    Position move(double velocity, Direction direction);

    Position moveWithApple(double velocity, Direction direction);

    BodyHead getHead();

    Position getPosition();

    List<BodyPart> getBodyParts();

    int getLength();

    double getPartSize();

}
