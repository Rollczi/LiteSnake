package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.snake.listener.SnakeBreakListener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Collections;

public class SnakeMap extends BoundingBox {

    private final Set<Snake> snakes = new HashSet<>();
    private final Map<String, Snake> snakesByName = new HashMap<>();

    public SnakeMap(Position min, Position max) {
        super(min, max);
    }

    public Set<Snake> getSnakes() {
        return Collections.unmodifiableSet(snakes);
    }

    public Snake spawnSnake(String name) {
        if (snakesByName.containsKey(name)) {
            throw new IllegalStateException("This snake already exists!");
        }

        Snake snake = new SnakeFreeInSpace(name);

        snakes.add(snake);
        snakesByName.put(name, snake);






        return snake;
    }

}
