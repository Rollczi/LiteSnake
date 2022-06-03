package com.litedevelopers.snake.engine.fruits;

import com.litedevelopers.snake.engine.entity.Entity;
import com.litedevelopers.snake.engine.snake.Snake;

public interface Fruit extends Entity {

    FruitType getType();

    void applyOnSnake(Snake snake);

}
