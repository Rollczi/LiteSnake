package com.litedevelopers.snake.engine.entity;

import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.Position;

public interface Entity {

    Position getPosition();

    BoundingBox getBoundingBox();

}
