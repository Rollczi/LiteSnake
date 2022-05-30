package com.litedevelopers.snake.engine.platform;

import com.litedevelopers.snake.engine.math.Direction;
import com.litedevelopers.snake.engine.snake.Snake;

public interface PlayerInteraction {

    Direction getDirection(Snake snake);

}
