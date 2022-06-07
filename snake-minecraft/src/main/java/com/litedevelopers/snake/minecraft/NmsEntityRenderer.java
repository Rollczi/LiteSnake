package com.litedevelopers.snake.minecraft;

import com.litedevelopers.snake.engine.graphics.GraphicsElement;
import com.litedevelopers.snake.engine.graphics.GraphicsRenderer;
import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.snake.SnakeMap;

public class NmsEntityRenderer implements GraphicsRenderer<NmsEntity> {

    @Override
    public void updateMapGrid(SnakeMap snakeMap) {

    }

    @Override
    public void clearMapGrid() {

    }

    @Override
    public NmsEntity createBox(BoundingBox boundingBox, GraphicsElement.Type type) {
        return null;
    }

    @Override
    public NmsEntity createBox(BoundingBox boundingBox, float rotation, GraphicsElement.Type type) {
        return null;
    }

    @Override
    public void moveBox(NmsEntity graphicsBox, Position to) {

    }

    @Override
    public void rotateBox(NmsEntity graphicsBox, float rotation) {

    }

    @Override
    public void deleteBox(NmsEntity graphicsBox) {

    }

}
