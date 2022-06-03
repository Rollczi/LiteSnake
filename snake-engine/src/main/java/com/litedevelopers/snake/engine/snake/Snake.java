package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.math.RotatedBox;

import java.util.List;

public interface Snake {

    String getName();

    Position move(double velocity, Position direction);


    RotatedBox getHead();

    Position getPosition();

    List<RotatedBox> getBodyParts();

    void setLength(int length);

    int getLength();

    double getPartSize();

}
