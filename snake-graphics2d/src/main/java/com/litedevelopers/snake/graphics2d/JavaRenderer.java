package com.litedevelopers.snake.graphics2d;

import com.litedevelopers.snake.engine.graphics.GraphicsRenderer;
import com.litedevelopers.snake.engine.math.BoundingBox;
import com.litedevelopers.snake.engine.math.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

class JavaRenderer implements GraphicsRenderer<JavaElement2d> {

    private final JFrameWindow window;

    private JavaRenderer(JFrameWindow window) {
        this.window = window;
    }

    @Override
    public void refresh() {
        window.repaint();
    }

    @Override
    public JavaElement2d createBox(BoundingBox boundingBox) {
        Position min = boundingBox.getMinPosition();
        Position max = boundingBox.getMaxPosition();

        double v = max.getX() - min.getX();
        double v1 = max.getY() - min.getY();

        Rectangle2D rectangle2D = new Rectangle2D.Double(max.getX() - v1, max.getY() - v, v, v1);

        window.registerShape(rectangle2D);

        return new JavaElement2d(rectangle2D);
    }

    @Override
    public void deleteBox(JavaElement2d graphicsBox) {
        window.unregisterShape(graphicsBox.getShape());
    }

    @Override
    public void moveBox(JavaElement2d graphicsBox, Position from, Position to) {

    }

    public static JavaRenderer create() {
        AtomicReference<JFrameWindow> window = new AtomicReference<>();
        CountDownLatch latch = new CountDownLatch(1);

        SwingUtilities.invokeLater(() -> {
            JFrameWindow panel = new JFrameWindow();
            panel.setBackground(Color.WHITE.darker());

            JFrame frame = new JFrame("LiteSnake");

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

            frame.setSize(screenSize.width, screenSize.height);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(panel, BorderLayout.CENTER);
            frame.setVisible(true);

            window.set(panel);
            latch.countDown();
        });

        try {
            latch.await();
        } catch (InterruptedException exception) {
            throw new RuntimeException(exception);
        }

        return new JavaRenderer(window.get());
    }

    public JFrameWindow getWindow() {
        return window;
    }
}
