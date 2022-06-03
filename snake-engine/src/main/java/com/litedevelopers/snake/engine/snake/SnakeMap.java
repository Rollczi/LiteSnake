package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.fruits.Apple;
import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Collections;

public final class SnakeMap extends BoundingBox {

    public static SnakeMap CLOSED = new SnakeMap(Position.ZERO, Position.ZERO, true);

    private final Set<Snake> snakes = new HashSet<>();
    private final Map<String, Snake> snakesByName = new HashMap<>();
    private final List<Apple> apples = new ArrayList<>();

    private boolean closed;

    public SnakeMap(Position min, Position max) {
        super(min, max);
        this.closed = false;
    }

    private SnakeMap(Position min, Position max, boolean closed) {
        super(min, max);
        this.closed = closed;
    }

    public Set<Snake> getSnakes() {
        return Collections.unmodifiableSet(snakes);
    }

    public void spawnApple(Apple apple) {
        this.apples.add(apple);
    }

    public Snake killSnake(String name) {
        Snake snake = snakesByName.remove(name);

        if (snake == null) {
            throw new NullPointerException();
        }
        snakes.remove(snake);

        return snake;
    }

    public Snake spawnSnake(String name, double sizeHead) {
        if (this.closed) {
            throw new IllegalStateException("This map is closed!");
        }

        if (snakesByName.containsKey(name)) {
            throw new IllegalStateException("This snake already exists!");
        }

        Snake snake = new SnakeFreeInSpace(name, this.center(), sizeHead);

        this.snakes.add(snake);
        this.snakesByName.put(name, snake);

        return snake;
    }

    public Optional<Apple> getAppleIfStanding(Snake snake) {
        for (Apple apple : apples) {
            BoundingBox box = apple.getBoundingBox();

            if (box.contains(snake.getHead())) {
                return Optional.of(apple);
            }
        }

        return Optional.empty();
    }

    public void closeMap() {
        this.closed = true;
    }

    public boolean isClosed() {
        return this.closed;
    }

    public boolean isEmpty() {
        return this.snakes.isEmpty();
    }

}
