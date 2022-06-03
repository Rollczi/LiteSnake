package com.litedevelopers.snake.engine.event.fruit;

import com.litedevelopers.snake.engine.fruits.Fruit;

public class FruitSpawnEvent extends FruitEvent {

    public FruitSpawnEvent(Fruit fruit) {
        super(fruit);
    }

}
