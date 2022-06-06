package com.litedevelopers.snake.libgdx.render;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CameraScrollController implements InputProcessor {

    private final Viewport viewport;

    public CameraScrollController(Viewport viewport) {
        this.viewport = viewport;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        if (amountY == 0) {
            return false;
        }

        float w = amountY * viewport.getWorldWidth() / 10;
        float h = amountY * viewport.getWorldHeight() / 10;
        viewport.setWorldSize(viewport.getWorldWidth() + w, viewport.getWorldHeight() + h);

        return false;
    }

}
