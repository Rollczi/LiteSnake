package com.litedevelopers.snake.graphics2d;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

class JFrameWindow extends JPanel {

    private final Set<Shape> toRender = new HashSet<>();

    void registerShape(Shape shape) {
        toRender.add(shape);
    }

    void unregisterShape(Shape shape) {
        toRender.remove(shape);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        Point center = new Point(getWidth() / 2, getHeight() / 2);

        for (Shape shape : toRender) {
            graphics2D.setColor(new Color(18, 208, 18));
            graphics2D.fill(shape);
        }

        graphics2D.setColor(new Color(208, 18, 18));

        graphics.fillRect(10, 10, 100, 100);
    }

}
