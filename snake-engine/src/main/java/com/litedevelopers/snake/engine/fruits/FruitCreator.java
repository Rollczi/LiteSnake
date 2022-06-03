package com.litedevelopers.snake.engine.fruits;

import com.litedevelopers.snake.engine.math.Position;

@FunctionalInterface
public interface FruitCreator {

    Fruit create(Position position);

}
