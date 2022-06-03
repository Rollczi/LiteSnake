package com.litedevelopers.snake.libgdx.render;

import com.badlogic.gdx.math.Rectangle;
import com.litedevelopers.snake.engine.graphics.GraphicsElement;

public class LibgdxElement implements GraphicsElement {

    final Rectangle rectangle;
    final Type type;

    LibgdxElement(Rectangle rectangle, Type type) {
        this.rectangle = rectangle;
        this.type = type;
    }

}
