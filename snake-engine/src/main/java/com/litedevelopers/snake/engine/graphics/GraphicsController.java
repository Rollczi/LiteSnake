package com.litedevelopers.snake.engine.graphics;

import com.litedevelopers.snake.engine.snake.BodyPart;
import com.litedevelopers.snake.engine.snake.Snake;
import com.litedevelopers.snake.engine.snake.SnakeMap;
import com.litedevelopers.snake.engine.snake.event.Listener;
import com.litedevelopers.snake.engine.snake.event.Subscribe;
import com.litedevelopers.snake.engine.snake.event.map.SnakeCreateMapEvent;
import com.litedevelopers.snake.engine.snake.event.snake.SnakeMoveEvent;
import com.litedevelopers.snake.engine.snake.event.snake.SnakeSpawnEvent;

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

    @Subscribe
    public void onMapRender(SnakeCreateMapEvent event) {
        SnakeMap snakeMap = event.getSnakeMap();

        this.graphicsRenderer.createBox(snakeMap);
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

        boxes.add(this.graphicsRenderer.createBox(snake.getHead()));

        for (BodyPart bodyPart : snake.getBodyParts()) {
            boxes.add(this.graphicsRenderer.createBox(bodyPart));
        }

        this.graphicsElements.put(snake, boxes);
        this.graphicsRenderer.refresh();
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

        boxes.add(this.graphicsRenderer.createBox(snake.getHead()));

        for (BodyPart bodyPart : snake.getBodyParts()) {
            boxes.add(this.graphicsRenderer.createBox(bodyPart));
        }

        this.graphicsElements.put(snake, boxes);
        this.graphicsRenderer.refresh();
    }

}
