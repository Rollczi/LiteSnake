package com.litedevelopers.snake.minecraft.player;

import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.player.PlayerInteraction;
import com.litedevelopers.snake.engine.snake.Snake;
import com.litedevelopers.snake.engine.snake.SnakeMap;

final class PlayerState implements PlayerInteraction {

   Position last = new Position(0, 1);
   boolean boosting = false;
   Position camera = new Position(0, 1);

    @Override
    public Position getDirection(SnakeMap snakeMap, Snake snake) {
        return last;
    }

    @Override
    public boolean isBoosting() {
        return boosting;
    }

    @Override
    public void updateCamera(Position position) {
        this.camera = camera;
    }

}
