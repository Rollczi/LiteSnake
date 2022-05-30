package com.litedevelopers.snake.graphics2d;

import com.litedevelopers.snake.engine.math.Direction;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.platform.PlayerInteraction;
import com.litedevelopers.snake.engine.snake.Snake;

import javax.swing.*;
import java.awt.*;

class JavaPlayerInteraction implements PlayerInteraction {

    private final static int POINT_IGNORE = 25;

    private final JPanel jPanel;
    private Direction last = new Direction(0, 10);
    private Point lastPoint = new Point(0, 0);

    JavaPlayerInteraction(JPanel jPanel) {
        this.jPanel = jPanel;
    }

    @Override
    public Direction getDirection(Snake snake) {
        Position position = snake.getPosition();
        Point point = jPanel.getMousePosition();

        if (point == null) {
            return last;
        }

        point = new Point(point.x, point.y);


//        if (point.x == this.lastPoint.x || point.y == this.lastPoint.y) {
//            return last;
//        }
//
//        if (position.distance(point.x, point.y) < 50) {
//            if (this.lastPoint.x >= point.x - POINT_IGNORE  && this.lastPoint.x <= point.x + POINT_IGNORE) {
//                return last;
//            }
//
//            if (this.lastPoint.y >= point.y - POINT_IGNORE  && this.lastPoint.y <= point.y + POINT_IGNORE) {
//                return last;
//            }
//        }

        int x = point.x - position.getIntX();
        int y = point.y - position.getIntY();

        if (x == 0 && y == 0) {
            return last;
        }

        this.lastPoint = new Point(point.x, point.y);
        return this.last = new Direction(x, y);
    }

}
