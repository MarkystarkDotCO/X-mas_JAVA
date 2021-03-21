package engine1;

import java.awt.*;
import java.awt.geom.Path2D;
import javax.swing.*;
import java.lang.Math;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.geom.Path2D.Double;

public class Engine1 extends JPanel {

    private Path2D mmoon;
    private Path2D rroad;

    public Engine1() {
        mmoon = new Path2D.Double();
        mmoon.moveTo(500, 25);
        mmoon.curveTo(500, 70, 400, 20, 550, 15);
        mmoon.closePath();
        
        rroad = new Path2D.Double();
        rroad.moveTo(0, 0);
        rroad.curveTo(0, 10, 400, 20, 100, 600);
        rroad.closePath();
    }

    public static void main(String[] args) {
        Engine1 mgs = new Engine1();

        JFrame f = new JFrame();
        f.add(mgs);
        f.setTitle("Engine1");
        f.setSize(600, 600);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }

    public void paintComponent(Graphics g) {
        background(g);
        moon(g);
        xmasTree(g, 150, 0, 100);
        xmasTree(g, 200, 50, 100);
        xmasTree(g, 250, 100, 100);
        xmasTree(g, 300, 150, 100);
        xmasTree(g, 350, 200, 100);
        xmasTree(g, 400, 250, 100);
        xmasTree(g, 450, 300, 100);
        xmasTree(g, 500, 350, 110);

        xmasTree(g, 0, 150, 100);
        xmasTree(g, 50, 200, 100);
        xmasTree(g, 100, 250, 100);
        xmasTree(g, 100, 300, 100);
        xmasTree(g, 50, 350, 100);
        xmasTree(g, 0, 400, 100);
    }

    private void plot(Graphics g, int x, int y, int size) {
        g.fillRect(x, y, size, size);
    }

    private void trangle(Graphics g, int x, int y, int i) {
        g.fillPolygon(new int[]{x - 50 - i, x, x + 50 + i}, new int[]{30 + y, y, 30 + y}, 3);
    }

    private void xmasTree(Graphics g, int x, int y, int size) {
        int i = 0;
        for (i = 1; i < size; i += 10) {
            g.setColor(new Color(100 + i / 2, i / 2, 60 + i / 4, 250));
            trangle(g, x, y + i, i / 3);
            snow(g, x, y);

        }
        g.setColor(new Color(224, 187, 228, 255));
        g.fillPolygon(new int[]{0 + x - 10, 25 + x - 10, 25 + x - 10}, new int[]{0 + y + i + 20, 0 + y + i + 20, 50 + y + i + 20}, 3);
        g.fillPolygon(new int[]{0 + x - 10, 0 + x - 10, 25 + x - 10}, new int[]{0 + y + i + 20, 50 + y + i + 20, 50 + y + i + 20}, 3);
    }

    private void background(Graphics g) {
        for (int i = 0; i < 600; i++) {
            g.setColor(new Color(255, 255 - i / 10, 200, 255));
            ddaLine(g, 0, i, 600, i);
        }
        g.setColor(new Color(146, 160, 207, 255));
        g.fillPolygon(new int[]{0, 600, 600}, new int[]{0, 0, 600}, 3);
        g.setColor(new Color(124, 139, 255, 250));
        g.fillPolygon(new int[]{0, 0, 200}, new int[]{200, 600, 600}, 3);
        g.setColor(new Color(0, 255, 255, 255));
        for (int i = 0; i < 1000; i++) {
            int x = i - (int) Math.random();
            int y = i - (int) Math.random();
            snow(g, 600, 600);
        }

    }

    private void snow(Graphics g, int x, int y) {
        g.setColor(new Color(255, 255, 255, 255));
        ddaLine(g, x + 0, y + 0, x + 10, y + 10);
        ddaLine(g, x + 10, y + 0, x + 0, y + 10);
    }

    private void moon(Graphics g) {
        g.setColor(Color.YELLOW);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.fill(mmoon);
        g2d.dispose();
    }

    private void road(Graphics g) {
        g.setColor(Color.GRAY);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.fill(rroad);
        g2d.dispose();
    }

    private void rectangle(Graphics g, int x, int y, Color c) {

        g.setColor(c);
        g.fillPolygon(new int[]{0 + x, 100 + x, 100 + x}, new int[]{0 + y, 0 + y, 300 + y}, 3);
        g.fillPolygon(new int[]{0 + x, 0 + x, 100 + x}, new int[]{0 + y, 300 + y, 300 + y}, 3);
    }

    private void stars(Graphics g, int x, int y, int s) {
        g.setColor(Color.YELLOW);
        g.fillPolygon(new int[]{0 + x, 10 * s + x, 10 * s + x}, new int[]{10 * s + y, 0 + y, 20 * s + y}, 3);
        g.fillPolygon(new int[]{10 * s + x, 10 * s + x, 20 * s + x}, new int[]{0 + y, 20 * s + y, 10 * s + y}, 3);
    }

    public void ddaLine(Graphics g, int x1, int y1, int x2, int y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        double x, y;
        double m = dy / dx;

        if (m <= 1 && m >= 0) {
            y = Math.min(y1, y2);
            for (x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                y += m;
                plot(g, (int) x, (int) y, 3);
            }
        } else if (m >= -1 && m < 0) {
            y = Math.min(y1, y2);
            for (x = Math.max(x1, x2); x >= Math.min(x1, x2); x--) {
                y -= m;
                plot(g, (int) x, (int) y, 3);
            }
        } else if (m > 1) {
            x = Math.min(x1, x2);
            for (y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                x += 1 / m;
                plot(g, (int) x, (int) y, 3);
            }
        } else {
            x = Math.min(x1, x2);
            for (y = Math.max(y1, y2); y >= Math.min(y1, y2); y--) {
                x -= 1 / m;
                plot(g, (int) x, (int) y, 3);
            }
        }
    }
}
