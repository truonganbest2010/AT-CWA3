package view;

import javax.swing.*;

import view.ClickGamePanel.GameState;

import java.awt.*;
import java.util.ArrayList;
import model.Shape;


public class QuestCanvas extends JPanel {
    
    private ClickGamePanel panel;
    private int shapeNo;
    private Color colorQuest;
    private ArrayList<Shape> timeBar = new ArrayList<>();

    public QuestCanvas(ClickGamePanel panel){
        this.panel = panel;
        setPreferredSize(new Dimension(100, 100));
        setBackground(Color.black);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        if (panel.getGameState() == GameState.READY){
            g2.setColor(Color.white);
            g2.drawOval(20, 18, 60, 60);
            g2.setFont(new Font("Courier", Font.BOLD, 15));
            g2.drawString("^", 35, 42);
            g2.drawString("^", 55, 42);
            g2.drawString("___", 35, 60);
            repaint();
            
        }
        else if (panel.getGameState() == GameState.PLAYING){
            if (colorQuest != null){
                g2.setColor(colorQuest);
            }
            if (shapeNo == 0){
                g2.drawOval(20, 18, 60, 60);
                g2.fillOval(20+2, 18+2, 60-4, 60-4);
            } else if (shapeNo == 1){
                g2.drawRect(20, 18, 60, 60);
                g2.fillRect(20+2, 18+2, 60-4, 60-4);
            } else if (shapeNo == 2){
                int[] x = new int[3]; x[0]=20; x[1]=20; x[2]=20+60;
                int[] y = new int[3]; y[0]=18; y[1]=18+60; y[2]=18+60;
                g2.fillPolygon(x, y, 3);
            } else if (shapeNo == 3){
                int[] x = new int[3]; x[0]=20+60; x[1]=20; x[2]=20+60;
                int[] y = new int[3]; y[0]=18; y[1]=18+60; y[2]=18+60;
                g2.fillPolygon(x, y, 3);
            }

            for (var i: timeBar){
                i.render(g2);
            }
        }
        else if (panel.getGameState() == GameState.GAMEOVER){
            g2.setColor(Color.white);
            g2.drawOval(20, 18, 60, 60);
            g2.setFont(new Font("Courier", Font.BOLD, 15));
            g2.drawString("X", 37, 42);
            g2.drawString("X", 58, 42);
            g2.drawString("___", 38, 58);
            repaint();
        }
    }


    public ArrayList<Shape> getTimeBar(){
        return timeBar;
    }

    public void setColorQuest(Color color){
        this.colorQuest = color;
    }

    public void setShapeNo(int shapeNo){
        this.shapeNo = shapeNo;
    }

    public Color getColorQuest(){
        return colorQuest;
    }

    public int getShapeNo(){
        return shapeNo;
    }
}