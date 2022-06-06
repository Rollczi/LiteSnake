package com.litedevelopers.snake.engine.graphics;

import com.litedevelopers.snake.engine.event.fruit.FruitEatEvent;
import com.litedevelopers.snake.engine.event.fruit.FruitSpawnEvent;
import com.litedevelopers.snake.engine.event.snake.SnakeDeathEvent;
import com.litedevelopers.snake.engine.fruits.Fruit;
import com.litedevelopers.snake.engine.fruits.FruitType;
import com.litedevelopers.snake.engine.math.MathUtils;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.math.RotatedBox;
import com.litedevelopers.snake.engine.snake.Snake;
import com.litedevelopers.snake.engine.snake.SnakeMap;
import com.litedevelopers.snake.engine.event.Listener;
import com.litedevelopers.snake.engine.event.Subscribe;
import com.litedevelopers.snake.engine.event.map.SnakeCreateMapEvent;
import com.litedevelopers.snake.engine.event.map.SnakeDeleteMapEvent;
import com.litedevelopers.snake.engine.event.snake.SnakeMoveEvent;
import com.litedevelopers.snake.engine.event.snake.SnakeSpawnEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GraphicsController<T extends GraphicsElement> implements Listener {

    private final Random random = new Random();
    private final GraphicsRenderer<T> graphicsRenderer;

    public GraphicsController(GraphicsRenderer<T> graphicsRenderer) {
        this.graphicsRenderer = graphicsRenderer;
    }

    private final Map<Snake, T> heads = new HashMap<>();
    private final Map<Snake, List<T>> bodies = new HashMap<>();
    private final Map<Fruit, T> fruits = new HashMap<>();

    @Subscribe
    public void onMapRender(SnakeCreateMapEvent event) {
        SnakeMap snakeMap = event.getSnakeMap();
        this.graphicsRenderer.updateMapGrid(snakeMap);
    }

    @Subscribe
    public void onMapRender(SnakeDeleteMapEvent event) {
        this.graphicsRenderer.clearMapGrid();
    }

    @Subscribe
    public void onSpawn(FruitSpawnEvent event) {
        Fruit fruit = event.getFruit();
        FruitType type = fruit.getType();

        Position direction = new Position(random.nextDouble(), random.nextDouble());
        double angle = MathUtils.toAngle(direction);

        T box = this.graphicsRenderer.createBox(fruit.getBoundingBox(), (float) angle, GraphicsUtils.convert(type));

        fruits.put(fruit, box);
    }

    @Subscribe
    public void onSpawn(FruitEatEvent event) {
        Fruit fruit = event.getFruit();
        T removed = fruits.remove(fruit);

        if (removed == null) {
            return;
        }

        this.graphicsRenderer.deleteBox(removed);
    }

    @Subscribe
    public void onSpawnSnake(SnakeSpawnEvent event) {
        this.onSnakeCreateOrUpdate(event.getSnake());
    }

    @Subscribe
    public void onMoveSnake(SnakeMoveEvent event) {
        this.onSnakeCreateOrUpdate(event.getSnake());
    }

    private void onSnakeCreateOrUpdate(Snake snake) {
        List<T> boxes = this.bodies.getOrDefault(snake, new ArrayList<>());
        Iterator<RotatedBox> iterator = snake.getBodyParts().iterator();

        for (T box : new ArrayList<>(boxes)) {
            if (!iterator.hasNext()) {
                graphicsRenderer.deleteBox(box);
                boxes.remove(box);
                continue;
            }

            RotatedBox next = iterator.next();

            graphicsRenderer.moveBox(box, next.center());
            graphicsRenderer.rotateBox(box, next.rotation());
        }

        while (iterator.hasNext()) {
            RotatedBox next = iterator.next();
            T box = this.graphicsRenderer.createBox(next, next.rotation(), GraphicsElement.Type.SNAKE_BODY);

            boxes.add(box);
        }

        this.bodies.put(snake, boxes);

        T head = heads.get(snake);

        if (head != null) {
            this.graphicsRenderer.moveBox(head, snake.getHeadPosition());
            this.graphicsRenderer.rotateBox(head, snake.getHead().rotation());
            return;
        }

        this.heads.put(snake, this.graphicsRenderer.createBox(snake.getHead(), snake.getHead().rotation(), GraphicsElement.Type.SNAKE_HEAD));
    }

    @Subscribe
    public void onSnakeDeath(SnakeDeathEvent event) {
        Snake snake = event.getSnake();
        List<T> boxes = this.bodies.remove(snake);

        if (boxes != null) {
            for (T box : boxes) {
                this.graphicsRenderer.deleteBox(box);
            }
        }

        T head = heads.remove(snake);

        if (head != null) {
            this.graphicsRenderer.deleteBox(head);
        }
    }

}
