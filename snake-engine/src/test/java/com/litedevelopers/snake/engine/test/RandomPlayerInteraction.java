package com.litedevelopers.snake.engine.test;

import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.platform.PlayerInteraction;

import java.util.Random;

class RandomPlayerInteraction implements PlayerInteraction {

    private final Random random = new Random();

    @Override
    public Position getDirection() {
        return new Position(random.nextDouble(), random.nextDouble());
    }

}
