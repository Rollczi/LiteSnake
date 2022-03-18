package com.litedevelopers.snake.engine.snake;

import com.litedevelopers.snake.engine.snake.listener.SnakeBreakListener;
import com.litedevelopers.snake.engine.snake.listener.SnakeMoveListener;
import com.litedevelopers.snake.engine.math.Position;

import java.util.HashSet;
import java.util.Set;

public class SnakeFreeInSpace implements Snake {

    private final String name;
    private Position position = new Position(0, 0);
    private int tailLength = 1;

    private final Set<SnakeMoveListener> moveListeners = new HashSet<>();
    private final Set<SnakeBreakListener> breakListeners = new HashSet<>();

    SnakeFreeInSpace(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public int getTailLength() { // Imperium
        return tailLength;
    }

    @Override
    public void setTailLength(int tailLength) {
        this.tailLength = tailLength;
    }

    @Override
    public void addMoveLister(SnakeMoveListener listener) {
        this.moveListeners.add(listener);
    }

    @Override
    public void addBreakLister(SnakeBreakListener listener) {
        this.breakListeners.add(listener);
    }

    @Override
    public void breakSnake() {
        for (SnakeBreakListener listener : this.breakListeners) {
            listener.handle(this);
        }
    }

    @Override
    public void moveTo(Position position) {

        this.position = position;
        for (SnakeMoveListener listener : this.moveListeners) {
            listener.handle(this);
        }
    }

}