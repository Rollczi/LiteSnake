package com.litedevelopers.snake.engine.test;

import com.litedevelopers.snake.engine.graphics.GraphicsRenderer;
import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.Position;

import java.awt.*;

class AndroidRenderer implements GraphicsRenderer<AndroidElement> {

    @Override
    public AndroidElement createBox(BoundingBox boundingBox) {
        Position minPosition = boundingBox.getMinPosition();
        Position maxPosition = boundingBox.getMaxPosition();

        Shape shape = new Rectangle(minPosition.getIntX(), minPosition.getIntY(), maxPosition.getIntX(), maxPosition.getIntX());

//        layout.add(shape);

        return new AndroidElement(shape);
    }

    @Override
    public void deleteBox(AndroidElement graphicsBox) {
        Shape shape = graphicsBox.getShape();
//        layout.remove(shape);
    }

    @Override
    public void moveBox(AndroidElement graphicsBox, Position from, Position to) {
        Shape shape = graphicsBox.getShape();

//        shape.setX(to.getX());
//        shape.setY(to.getY());
    }

}
