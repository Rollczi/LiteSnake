package com.litedevelopers.snake.engine.test;

import com.litedevelopers.snake.engine.graphics.GraphicsElement;

import java.awt.*;

class AndroidElement implements GraphicsElement {

    private final Shape shape;

    AndroidElement(Shape shape) {
        this.shape = shape;
    }

    public Shape getShape() {
        return shape;
    }

}
