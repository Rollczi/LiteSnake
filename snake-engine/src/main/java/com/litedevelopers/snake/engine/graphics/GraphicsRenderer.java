package com.litedevelopers.snake.engine.graphics;

import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.snake.SnakeMap;

public interface GraphicsRenderer<T extends GraphicsElement> {

    void updateMapGrid(SnakeMap snakeMap);

    void clearMapGrid();

    T createBox(BoundingBox boundingBox, GraphicsElement.Type type);

    T createBox(BoundingBox boundingBox, float rotation, GraphicsElement.Type type);

    void moveBox(T graphicsBox, Position to);

    void rotateBox(T graphicsBox, float rotation);

    void deleteBox(T graphicsBox);

}
