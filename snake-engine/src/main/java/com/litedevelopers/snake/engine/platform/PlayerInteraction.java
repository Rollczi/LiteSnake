package com.litedevelopers.snake.engine.platform;

import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.snake.Snake;

public interface PlayerInteraction {

    Position getDirection(Snake snake);

    void setCamera(Position position);

}
