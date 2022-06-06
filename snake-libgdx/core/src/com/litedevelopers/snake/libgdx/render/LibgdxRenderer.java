package com.litedevelopers.snake.libgdx.render;

import com.badlogic.gdx.math.Rectangle;
import com.litedevelopers.snake.engine.graphics.GraphicsElement;
import com.litedevelopers.snake.engine.graphics.GraphicsRenderer;
import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.snake.SnakeMap;

public class LibgdxRenderer implements GraphicsRenderer<LibgdxElement> {

    private final LibgdxGraphicsApplication application;

    public LibgdxRenderer(LibgdxGraphicsApplication application) {
        this.application = application;
    }

    @Override
    public void updateMapGrid(SnakeMap map) {
        this.application.map = map;
    }

    @Override
    public void clearMapGrid() {
        this.application.map = SnakeMap.CLOSED;
    }

    @Override
    public LibgdxElement createBox(BoundingBox boundingBox, GraphicsElement.Type type) {
        return createBox(boundingBox, 0.0F, type);
    }

    @Override
    public LibgdxElement createBox(BoundingBox boundingBox, float rotation, GraphicsElement.Type type) {
        LibgdxElement element = new LibgdxElement(new Rectangle(
                (float) boundingBox.getMin().getX(),
                (float) boundingBox.getMin().getY(),
                (float) boundingBox.width(),
                (float) boundingBox.height()
        ), rotation, type);

        this.application.addElement(element);

        return element;
    }

    @Override
    public void deleteBox(LibgdxElement graphicsBox) {
        this.application.removeElement(graphicsBox);
    }

    @Override
    public void moveBox(LibgdxElement graphicsBox, Position to) {
        graphicsBox.rectangle.x = (float) to.getX() - graphicsBox.rectangle.width / 2;
        graphicsBox.rectangle.y = (float) to.getY() - graphicsBox.rectangle.height / 2;
    }

    @Override
    public void rotateBox(LibgdxElement graphicsBox, float rotation) {
        graphicsBox.rotation = rotation;
    }

}
