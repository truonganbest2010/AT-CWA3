package model;

import java.awt.*;

public class Circle extends Shape {
    
    private int radius;

    public Circle(int x, int y, Color color, int radius){
        super(x, y, color);
        this.radius = radius;
    }

    @Override
    public void render(Graphics2D g2) {
        // TODO Auto-generated method stub
        g2.setColor(super.getColor());
        g2.drawOval(super.getX(), super.getY(), radius, radius);
        g2.fillOval(super.getX()+2, super.getY()+2, radius-4, radius-4);
    }
}