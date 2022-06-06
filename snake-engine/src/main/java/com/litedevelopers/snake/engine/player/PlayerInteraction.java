package com.litedevelopers.snake.engine.player;

import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.snake.Snake;
import com.litedevelopers.snake.engine.snake.SnakeMap;

public interface PlayerInteraction {

    Position getDirection(SnakeMap snakeMap, Snake snake);

    boolean isBoosting();

    void updateCamera(Position position);

}
