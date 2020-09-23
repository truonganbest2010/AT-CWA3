package model;

import java.awt.*;

public class Line extends Shape {

    private int x2;
    private int y2;

    public Line(int x, int y, Color color, int x2, int y2) {
        super(x, y, color);
        // TODO Auto-generated constructor stub
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void render(Graphics2D g2) {
        // TODO Auto-generated method stub
        g2.setColor(super.getColor());
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(super.getX(), super.getY(), x2, y2);
    }
    
}