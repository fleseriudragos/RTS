package Lab2.App4;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CircleGUI extends JFrame {

    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 400;
    private static final int SHAPE_SIZE = 50;

    private JPanel mainPanel;
    private SquarePanel square1;
    private SquarePanel square2;
    private SquarePanel square3;
    private Thread thread1, thread2, thread3;
    private boolean isRunning = true;

    public CircleGUI() {
        setTitle("Three Threads GUI");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the main panel and add it to the frame
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 3));
        add(mainPanel);

        // create the square panels and add them to the main panel
        square1 = new SquarePanel(Color.RED);
        mainPanel.add(square1);

        square2 = new SquarePanel(Color.BLUE);
        mainPanel.add(square2);

        square3 = new SquarePanel(Color.GREEN);
        mainPanel.add(square3);



        // start the threads
        thread1 = new Thread(square1);
        thread2 = new Thread(square2);
        thread3 = new Thread(square3);
        thread1.start();
        thread2.start();
        thread3.start();

        setVisible(true);
    }

    public static void main(String[] args) {
        new CircleGUI();
        new CircleShape();

    }

    // class for the square panels
    private class SquarePanel extends JPanel implements Runnable {

        private int x, y;
        private int minSpeed = 1, maxSpeed = 5;
        private int speed;
        private Color color;
        private Image bufferImage;
        private Graphics bufferGraphics;

        public SquarePanel(Color color) {
            this.color = color;
            setPreferredSize(new Dimension(SHAPE_SIZE, SHAPE_SIZE));
            x = (int) (Math.random() * (WINDOW_WIDTH - SHAPE_SIZE));
            y = 0;
        }

        public void run() {
            while (isRunning) {
                try {
                    // set a random speed for the square
                    speed = new Random().nextInt((maxSpeed - minSpeed) + 1) + minSpeed;
                    y += speed;

                    // move the square
                    setLocation(x, y);
                    repaint();

                    // check if the square has exited the window
                    if (y > WINDOW_HEIGHT) {
                        stopThread();
                    }

                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void paintComponent(Graphics g) {
            if (bufferImage == null) {
                bufferImage = createImage(SHAPE_SIZE, SHAPE_SIZE);
                bufferGraphics = bufferImage.getGraphics();
            }

            // clear the buffer
            bufferGraphics.clearRect(0, 0, SHAPE_SIZE, SHAPE_SIZE);

            // draw the rectangle on the buffer
            bufferGraphics.setColor(color);
            bufferGraphics.fillRect(0, 0, SHAPE_SIZE, SHAPE_SIZE);

            // copy the buffer to the screen
            g.drawImage(bufferImage, 0, 0, null);
        }

        public void stopThread() {
            isRunning = false;
        }

    }


}