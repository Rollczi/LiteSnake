package com.litedevelopers.snake.engine.graphics;

import com.litedevelopers.snake.engine.fruits.FruitType;

class GraphicsUtils {

    static GraphicsElement.Type convert(FruitType fruitType) {
        switch (fruitType) {
            case APPLE: return GraphicsElement.Type.APPLE;
            case COCONUT: return GraphicsElement.Type.COCONUT;
            default: throw new UnsupportedOperationException();
        }
    }

}
