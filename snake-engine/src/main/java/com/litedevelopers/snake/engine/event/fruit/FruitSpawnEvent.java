package com.litedevelopers.snake.engine.event.fruit;

import com.litedevelopers.snake.engine.fruits.Fruit;

public class FruitSpawnEvent extends FruitEvent {

    protected FruitSpawnEvent(Fruit fruit) {
        super(fruit);
    }

}
