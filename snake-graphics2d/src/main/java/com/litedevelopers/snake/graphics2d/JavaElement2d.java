package com.litedevelopers.snake.graphics2d;

import com.litedevelopers.snake.engine.graphics.GraphicsElement;

import java.awt.*;

class JavaElement2d implements GraphicsElement {

    private final Shape shape;

    JavaElement2d(Shape shape) {
        this.shape = shape;
    }

    Shape getShape() {
        return shape;
    }

}
