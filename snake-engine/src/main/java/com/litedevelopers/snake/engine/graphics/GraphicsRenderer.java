package com.litedevelopers.snake.engine.graphics;

import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.Position;

public interface GraphicsRenderer<T extends GraphicsElement> {

    T createBox(BoundingBox boundingBox);

    void deleteBox(T graphicsBox);

    void moveBox(T graphicsBox, Position from, Position to);

}
