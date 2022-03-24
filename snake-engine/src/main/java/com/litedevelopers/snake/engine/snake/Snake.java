package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.Position;

import java.util.List;

public interface Snake {

    String getName();

    void move(Position position); // TODO: Przyjmować prędkość i kierunek ruchu

    void moveWithApple(Position position);

    int getLength();

    void setHeadPosition(Position position);

    Position getHeadPosition();

    List<Position> getPosition();
}
