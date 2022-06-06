package com.litedevelopers.snake.libgdx;

import com.badlogic.gdx.math.Vector3;
import com.litedevelopers.snake.engine.math.Position;
import com.litedevelopers.snake.engine.player.PlayerInteraction;
import com.litedevelopers.snake.engine.snake.Snake;
import com.litedevelopers.snake.engine.snake.SnakeMap;

public class LibgdxPlayerInteraction implements PlayerInteraction {

    private final Vector3 camera = new Vector3(0, 0, 1);
    private Position last = new Position(0, 1);
    private boolean boosting = false;

    @Override
    public Position getDirection(SnakeMap snakeMap, Snake snake) {
        return last;
    }

    @Override
    public boolean isBoosting() {
        return this.boosting;
    }

    @Override
    public void updateCamera(Position camera) {
        this.camera.set((float) camera.getX(), (float) camera.getY(), 1);
    }

    public void setDirection(Position last) {
        this.last = last;
    }

    public void setBoosting(boolean boosting) {
        this.boosting = boosting;
    }

    public Vector3 getCamera() {
        return camera;
    }

}
