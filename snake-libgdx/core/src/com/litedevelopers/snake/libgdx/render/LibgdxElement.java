package com.litedevelopers.snake.libgdx.render;

import com.badlogic.gdx.math.Rectangle;
import com.litedevelopers.snake.engine.graphics.GraphicsElement;

public class LibgdxElement implements GraphicsElement {

    final Rectangle rectangle;
    final float rotation;
    final Type type;

    LibgdxElement(Rectangle rectangle, float rotation, Type type) {
        this.rectangle = rectangle;
        this.rotation = rotation;
        this.type = type;
    }

}
