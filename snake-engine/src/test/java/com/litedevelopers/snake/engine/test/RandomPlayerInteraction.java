package com.litedevelopers.snake.engine.test;

import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.player.PlayerInteraction;
import com.litedevelopers.snake.engine.snake.Snake;
import com.litedevelopers.snake.engine.snake.SnakeMap;

import java.util.Random;

class RandomPlayerInteraction implements PlayerInteraction {

    private final Random random = new Random();

    @Override
    public Position getDirection(SnakeMap snakeMap, Snake snake) {
        return null;
    }

    @Override
    public boolean isBoosting() {
        return false;
    }

    @Override
    public void updateCamera(Position position) {

    }
}
