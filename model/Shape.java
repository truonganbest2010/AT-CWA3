package model;

import java.awt.*;

public abstract class Shape {
    
    private int x;
    private int y;

    private Color color;

    public Shape(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Color getColor(){
        return color;
    }

    public abstract void render(Graphics2D g2);
}