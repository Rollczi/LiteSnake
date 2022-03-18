package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.snake.listener.SnakeBreakListener;
import com.litedevelopers.snake.engine.snake.listener.SnakeMoveListener;
import com.litedevelopers.snake.engine.math.Position;

public interface Snake {
    String getName();

    Position getPosition();

    void setPosition(Position position);

    int getTailLength();

    void setTailLength(int tailLength);

    void addMoveLister(SnakeMoveListener listener);

    void addBreakLister(SnakeBreakListener listener);

    void breakSnake();

    void moveTo(Position position);
}
