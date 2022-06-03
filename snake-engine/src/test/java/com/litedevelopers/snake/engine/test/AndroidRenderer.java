package com.litedevelopers.snake.engine.test;

import com.litedevelopers.snake.engine.graphics.GraphicsElement;
import com.litedevelopers.snake.engine.graphics.GraphicsRenderer;
import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.Position;

class AndroidRenderer implements GraphicsRenderer<AndroidElement> {

    @Override
    public AndroidElement createBox(BoundingBox boundingBox, GraphicsElement.Type type) {
        return new AndroidElement();
    }

    @Override
    public void deleteBox(AndroidElement graphicsBox) {

    }

    @Override
    public void moveBox(AndroidElement graphicsBox, Position from, Position to) {

    }

}
