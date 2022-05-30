package com.litedevelopers.snake.engine.test;

import com.litedevelopers.snake.engine.math.Direction;
import com.litedevelopers.snake.engine.platform.PlayerInteraction;

import java.util.Random;

class RandomPlayerInteraction implements PlayerInteraction {

    private final Random random = new Random();

    @Override
    public Direction getDirection() {
        return new Direction(random.nextDouble(), random.nextDouble());
    }

}
