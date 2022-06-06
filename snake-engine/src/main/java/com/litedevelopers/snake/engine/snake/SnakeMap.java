package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.MathUtils;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.math.RotatedBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Collections;
import java.util.function.Predicate;

public final class SnakeMap extends BoundingBox {

    public static SnakeMap CLOSED = new SnakeMap(Position.ZERO, Position.ZERO, true);

    private final Random random = new Random();
    private final Set<Snake> snakes = new HashSet<>();
    private final Map<String, Snake> snakesByName = new HashMap<>();

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

    public Snake killSnake(String name) {
        Snake snake = snakesByName.remove(name);

        if (snake == null) {
            throw new NullPointerException();
        }
        snakes.remove(snake);

        return snake;
    }

    public Snake spawnSnake(String name, double sizeHead, int length) {
        if (this.closed) {
            throw new IllegalStateException("This map is closed!");
        }

        if (snakesByName.containsKey(name)) {
            throw new IllegalStateException("This snake already exists!");
        }

        Snake snake = new SnakeFreeInSpace(name, this.findFreePosition(), sizeHead, length);

        this.snakes.add(snake);
        this.snakesByName.put(name, snake);

        return snake;
    }

    public Position findFreePosition() {
        root: for (int i = 0; i < 100; i++) {
            Position position = MathUtils.random(this);

            for (Snake snake : snakes) {
                double minDistance = snake.getPartSize() * 16;

                if (snake.getHeadPosition().distance(position) < minDistance) {
                    continue root;
                }

                for (RotatedBox part : snake.getBodyParts()) {
                    if (part.center().distance(position) < minDistance) {
                        continue root;
                    }
                }
            }

            return position;
        }

        return this.center();
    }

    public List<Snake> getSnakes(Predicate<RotatedBox> filter) {
        List<Snake> snakes = new ArrayList<>();

        root:
        for (Snake snake : this.snakes) {
            if (filter.test(snake.getHead())) {
                snakes.add(snake);
                continue;
            }

            for (RotatedBox part : snake.getBodyParts()) {
                if (filter.test(part)) {
                    snakes.add(snake);
                    continue root;
                }
            }
        }

        return snakes;
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

    public boolean isOnSnake(Snake snake) {
        for (Snake other : this.snakes) {
            List<RotatedBox> parts = other.getBodyParts();

            for (int i = 0; i < parts.size(); i++) {
                RotatedBox part = parts.get(i);

                if (!part.contains(snake.getHead())) {
                    continue;
                }

                if (!other.equals(snake)) {
                    return true;
                }

                if (i < 3) {
                    continue;
                }

                return true;
            }
        }

        return false;
    }
}
