package org.example;

import java.awt.*;
import java.awt.Graphics;

public class MyFrame extends Frame {
    public MyFrame(String title) {
        super(title);
        setSize(200, 100);
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.setFont(new Font("Arial", Font.BOLD, 11));
        g.setColor(Color.red);
        g.drawString("DEMO Version", 5, 35);
    }
}
