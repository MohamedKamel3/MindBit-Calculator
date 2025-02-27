package calc.logic.grapher;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import calc.logic.grapher.expression.Function;
import calc.logic.grapher.parser.ExpressionParser;

public class Window extends JPanel implements MouseWheelListener, Runnable {

    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;

    private BufferedImage buff;
    private Graphics2D g2d;

    private ExpressionParser parser;
    private Function function;

    private double windowX, windowY, windowWidth, windowHeight;
    private Point mousePt;

    public static Window window;
    public static Thread thread;

    public Window(String expression) {
        addMouseWheelListener(this);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePt = e.getPoint();
                repaint();
            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int dx = e.getX() - mousePt.x;
                int dy = e.getY() - mousePt.y;
                windowX -= dx / (double) WIDTH * windowWidth;
                windowY += dy / (double) HEIGHT * windowHeight;
                mousePt = e.getPoint();
                repaint();
            }
        });
        setFocusable(true);
        requestFocusInWindow();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));

        buff = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g2d = buff.createGraphics();

        parser = new ExpressionParser();
        setExpression(expression);

        windowX = 0.0;
        windowY = 0.0;
        windowHeight = 2.0;
        windowWidth = windowHeight * WIDTH / HEIGHT;
    }

    // Time variables
    private double yVar = 0.0;  // Constantly increasing
    private double zVar = 0.0;  // Cycles smoothly from -1 to 1

    private synchronized void updateDT(double dt) {
        yVar += dt;
        zVar = Math.sin(yVar);
    }

    public void setExpression(String expression) {
        this.function = parser.parse(expression);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        synchronized (this) {
            List<Double> xs = new ArrayList<>();
            List<Double> ys = new ArrayList<>();

            for (int x = 0; x < WIDTH; x++) {
                double xx = toRealX(x);

                double yy = 0.0;
                if (function != null) {
                    yy = function.evaluateAt(xx, yVar, zVar);
                }

                double scaledX = x;
                double scaledY = toScreenY(yy);
                scaledY = Math.min(Math.max(scaledY, -5), HEIGHT + 5);

                xs.add(scaledX);
                ys.add(scaledY);
            }

            int[] xa = new int[xs.size()];
            int[] ya = new int[ys.size()];
            for (int i = 0; i < xa.length; i++) {
                xa[i] = xs.get(i).intValue();
            }
            for (int i = 0; i < ya.length; i++) {
                ya[i] = ys.get(i).intValue();
            }

            g2d.setColor(Color.BLACK);
            int xAxisY = toScreenY(0.0);
            g2d.drawLine(0, xAxisY, WIDTH, xAxisY);
            int yAxisX = toScreenX(0.0);
            g2d.drawLine(yAxisX, 0, yAxisX, HEIGHT);

            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(new Color(50, 50, 180));
            g2d.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND));
            g2d.drawPolyline(xa, ya, xa.length);
        }

        g.drawImage(buff, 0, 0, null);
    }

    @Override
    public void run() {
        boolean running = true;

        long oldTime = 0;
        double dt = 0.0;

        while (running) {

            long newTime = System.nanoTime();
            dt = (newTime - oldTime) / 1000000000.0;
            oldTime = newTime;

            updateDT(dt);
            repaint();

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private double bottom() {
        return windowY - halfWindowHeight();
    }

    private double right() {
        return windowX - halfWindowWidth();
    }

    private double toRealX(int screenX) {
        return screenX / (double) WIDTH * windowWidth + right();
    }

    private double toRealY(int screenY) {
        return (HEIGHT - screenY) / (double) HEIGHT * windowHeight + bottom();
    }

    private int toScreenX(double realX) {
        return (int) ((realX - right()) / windowWidth * WIDTH);
    }

    private int toScreenY(double realY) {
        return HEIGHT - (int) ((realY - bottom()) / windowHeight * HEIGHT);
    }

    private double halfWindowWidth() {
        return windowWidth / 2.0;
    }

    private double halfWindowHeight() {
        return windowHeight / 2.0;
    }

    public static void showgraph(String expression) {
        JFrame frame = new JFrame("Function Grapher");
        Window window = new Window(expression);
        frame.add(window);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        new Thread(window).start();
    }

    public static void hidegraph() {
        thread.interrupt();
        window.setVisible(false);

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        double scale = Math.pow(1.15, e.getPreciseWheelRotation());
        double mxReal = toRealX(e.getX());
        double myReal = toRealY(e.getY());
        double sx = (windowX - mxReal) / windowWidth;
        double sy = (windowY - myReal) / windowHeight;
        windowWidth *= scale;
        windowHeight *= scale;
        windowX = mxReal + sx * windowWidth;
        windowY = myReal + sy * windowHeight;
    }
}
