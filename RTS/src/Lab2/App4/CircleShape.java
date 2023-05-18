package Lab2.App4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CircleShape extends JFrame implements KeyListener {
    private static final long serialVersionUID = 1L;

    private Circle circle;
    private JPanel panel;

    public CircleShape() {
        setTitle("Circle");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        circle = new Circle(100, 100, 50);
        panel = new CirclePanel(circle);
        panel.setPreferredSize(new Dimension(400, 400));

        add(panel);
        pack();

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                circle.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                circle.moveDown();
                break;
            case KeyEvent.VK_LEFT:
                circle.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                circle.moveRight();
                break;
        }
        panel.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}


class CirclePanel extends JPanel implements Runnable {
    private static final long serialVersionUID = 1L;

    private Circle circle;

    public CirclePanel(Circle circle) {
        this.circle = circle;
        setBackground(Color.WHITE);
        setPreferredSize(getPreferredSize());
        new Thread(this).start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillOval(circle.getX(), circle.getY(), circle.getSize(), circle.getSize());
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }
}

class Circle {
    private int x, y, size;

    public Circle(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public void moveUp() {
        y -= 5;
    }

    public void moveDown() {
        y += 5;
    }

    public void moveLeft() {
        x -= 5;
    }

    public void moveRight() {
        x += 5;
    }
}