package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.math.RotatedBox;

import java.util.List;

public interface Snake {

    String getName();

    Position move(double velocity, Position direction);

    RotatedBox getHead();

    Position getHeadPosition();

    Position getDirection();

    List<RotatedBox> getBodyParts();

    int getLength();

    void setLength(int length);

    double getBoost();

    void setBoost(double boost);

    void addBoost(double boost);

    double getPartSize();

}
