package com.litedevelopers.snake.libgdx;

import com.badlogic.gdx.math.Vector3;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.platform.PlayerInteraction;
import com.litedevelopers.snake.engine.snake.Snake;

public class LibgdxPlayerInteraction implements PlayerInteraction {

    private final Vector3 camera = new Vector3(0, 0, 1);
    private Position last = new Position(0, 1);
    private Position lastOk = new Position(0, 1);

    @Override
    public Position getDirection(Snake snake) {
        Position direction = last.subtract(snake.getHead().center());

        if (direction.getLength() < 30) {
            return lastOk;
        }

        double angleOld = Math.atan2(lastOk.getY(), lastOk.getX());
        double angleNow = Math.atan2(direction.getY(), direction.getX());

        if (Math.abs(angleNow - angleOld) > 90) {
            Position middle = direction
                    .add(lastOk)
                    .normalize();

            return new Position(middle.getX(), middle.getY());
        }

        lastOk = direction;
        return direction;
    }

    @Override
    public void setCamera(Position camera) {
        this.camera.set((float) camera.getX(), (float) camera.getY(), 1);
    }

    public void setDirection(Position last) {
        this.last = last;
    }

    public Vector3 getCamera() {
        return camera;
    }
}
