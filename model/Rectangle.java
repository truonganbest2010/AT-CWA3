package model;

import java.awt.*;

public class Rectangle extends Shape {
    
    private int width;
    private int height;

    public Rectangle(int x, int y, Color color, int width, int height){
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void render(Graphics2D g2) {
        // TODO Auto-generated method stub
        g2.setColor(super.getColor());
        g2.drawRect(super.getX(), super.getY(), width, height);
        g2.fillRect(super.getX()+2, super.getY()+2, width-4, height-4);
    }

}