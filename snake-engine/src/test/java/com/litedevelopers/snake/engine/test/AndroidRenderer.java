package com.litedevelopers.snake.engine.test;

import com.litedevelopers.snake.engine.graphics.GraphicsElement;
import com.litedevelopers.snake.engine.graphics.GraphicsRenderer;
import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.snake.SnakeMap;

class AndroidRenderer implements GraphicsRenderer<AndroidElement> {

    @Override
    public void updateMapGrid(SnakeMap snakeMap) {

    }

    @Override
    public void clearMapGrid() {

    }

    @Override
    public AndroidElement createBox(BoundingBox boundingBox, GraphicsElement.Type type) {
        return new AndroidElement();
    }

    @Override
    public AndroidElement createBox(BoundingBox boundingBox, float rotation, GraphicsElement.Type type) {
        return null;
    }

    @Override
    public void moveBox(AndroidElement graphicsBox, Position to) {

    }

    @Override
    public void rotateBox(AndroidElement graphicsBox, float rotation) {

    }

    @Override
    public void deleteBox(AndroidElement graphicsBox) {

    }

}
