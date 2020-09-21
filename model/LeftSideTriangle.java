package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.*;

public class LeftSideTriangle extends Shape {

    private int prefferedSize;
    private int[] xTriangle = new int[3];
    private int[] yTriangle = new int[3];
    private Polygon triangleFill;

    public LeftSideTriangle(int x, int y, Color color, int prefferedSize) {
        super(x, y, color);
        // TODO Auto-generated constructor stub
        this.prefferedSize = prefferedSize;
        setUp();
        triangleFill = new Polygon(xTriangle, yTriangle, 3);
    }

    private void setUp(){
        xTriangle[0] = super.getX();
        xTriangle[1] = super.getX();
        xTriangle[2] = super.getX() + prefferedSize;

        yTriangle[0] = super.getY();
        yTriangle[1] = super.getY() + prefferedSize;
        yTriangle[2] = super.getY() + prefferedSize;
    }

    @Override
    public void render(Graphics2D g2) {
        // TODO Auto-generated method stub
        g2.setColor(super.getColor());
        g2.fillPolygon(triangleFill);
    }
    
}