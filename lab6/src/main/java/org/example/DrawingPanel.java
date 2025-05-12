package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Random;


public class DrawingPanel extends JPanel {
    private  MainFrame frame;
    public int canvasWidth = 400, canvasHeight = 400;
    private final int dotSize = 20;

    private Point selectedPoint = null;

    private ArrayList<Point> dots;
    private ArrayList<Line> lines;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));
        setBackground(Color.WHITE);
        dots = new ArrayList<>();
        lines = new ArrayList<>();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleClick(e.getPoint());
            }
        });
    }

    public void generateDots(int count) {
        dots.clear();
        lines.clear(); // Clear existing lines too if any
        canvasWidth = getWidth();
        canvasHeight = getHeight();
        Random rand = new Random();
        for (int i = 0; i < count; i++) {
            int x = rand.nextInt(canvasWidth - dotSize) + dotSize / 2;
            int y = rand.nextInt(canvasHeight - dotSize) + dotSize / 2;
            dots.add(new Point(x, y));
        }
        repaint();
    }

    private void handleClick(Point click) {
        for (Point dot : dots) {
            if (click.distance(dot) <= dotSize / 2.0) {
                if (selectedPoint == null) {
                    selectedPoint = dot;
                } else {
                    if (!selectedPoint.equals(dot)) {
                        lines.add(new Line(selectedPoint, dot));
                    }
                    selectedPoint = null;
                    repaint();
                }
                break;
            }
        }
    }

    public void addLine(int index1, int index2) {
        if (index1 >= 0 && index2 >= 0 && index1 < dots.size() && index2 < dots.size()) {
            lines.add(new Line(dots.get(index1), dots.get(index2)));
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        paintDots(g);
        paintLines(g);
    }

    private void paintDots(Graphics2D g) {
        g.setColor(Color.BLUE);
        for (Point p : dots) {
            g.fillOval(p.x - dotSize / 2, p.y - dotSize / 2, dotSize, dotSize);
        }

        if (selectedPoint != null) {
            g.setColor(Color.RED);
            g.drawOval(selectedPoint.x - dotSize / 2 - 2, selectedPoint.y - dotSize / 2 - 2,
                    dotSize + 4, dotSize + 4);
        }
    }

    private void paintLines(Graphics2D g) {
        g.setColor(Color.BLACK);
        for (Line line : lines) {
            g.drawLine(line.p1.x, line.p1.y, line.p2.x, line.p2.y);
        }
    }


    private static class Line {
        Point p1, p2;

        Line(Point p1, Point p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }
}
