package com.litedevelopers.snake.engine.graphics;

import com.litedevelopers.snake.engine.event.fruit.FruitEatEvent;
import com.litedevelopers.snake.engine.event.fruit.FruitSpawnEvent;
import com.litedevelopers.snake.engine.fruits.Fruit;
import com.litedevelopers.snake.engine.fruits.FruitType;
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
import java.util.List;
import java.util.Map;

public class GraphicsController<T extends GraphicsElement> implements Listener {

    private final GraphicsRenderer<T> graphicsRenderer;

    public GraphicsController(GraphicsRenderer<T> graphicsRenderer) {
        this.graphicsRenderer = graphicsRenderer;
    }

    private final Map<Snake, List<T>> graphicsElements = new HashMap<>();
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

        T box = this.graphicsRenderer.createBox(fruit.getBoundingBox(), GraphicsUtils.convert(type));

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
        Snake snake = event.getSnake();
        List<T> oldBoxes = this.graphicsElements.get(snake);

        if (oldBoxes != null) {
            for (T oldBox : oldBoxes) {
                graphicsRenderer.deleteBox(oldBox);
            }
        }

        List<T> boxes = new ArrayList<>();

        boxes.add(this.graphicsRenderer.createBox(snake.getHead(), snake.getHead().rotation(), GraphicsElement.Type.SNAKE_HEAD));

        for (RotatedBox bodyPart : snake.getBodyParts()) {
            boxes.add(this.graphicsRenderer.createBox(bodyPart, bodyPart.rotation(), GraphicsElement.Type.SNAKE_BODY));
        }

        this.graphicsElements.put(snake, boxes);
    }

    @Subscribe
    public void onMoveSnake(SnakeMoveEvent event) {
        Snake snake = event.getSnake();
        List<T> oldBoxes = this.graphicsElements.get(snake);

        if (oldBoxes != null) {
            for (T oldBox : oldBoxes) {
                graphicsRenderer.deleteBox(oldBox);
            }
        }

        List<T> boxes = new ArrayList<>();

        boxes.add(this.graphicsRenderer.createBox(snake.getHead(), snake.getHead().rotation(), GraphicsElement.Type.SNAKE_HEAD));

        for (RotatedBox bodyPart : snake.getBodyParts()) {
            boxes.add(this.graphicsRenderer.createBox(bodyPart, bodyPart.rotation(), GraphicsElement.Type.SNAKE_BODY));
        }

        this.graphicsElements.put(snake, boxes);
    }

}
