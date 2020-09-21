package view;

import javax.swing.*;

import view.ClickGamePanel.GameState;

import java.awt.*;


public class QuestCanvas extends JPanel {
    
    private ClickGamePanel panel;
    private int shapeNo;
    private Color colorQuest;

    public QuestCanvas(ClickGamePanel panel){
        this.panel = panel;
        setPreferredSize(new Dimension(100, 100));
        setBackground(Color.black);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        if (panel.getGameState() == GameState.READY){

        }

        else if (panel.getGameState() == GameState.PLAYING){
            if (colorQuest != null){
                g2.setColor(colorQuest);
            }
            if (shapeNo == 0){
                g2.drawOval(30, 30, 60, 60);
                g2.fillOval(30+2, 30+2, 60-4, 60-4);
            } else if (shapeNo == 1){
                g2.drawRect(30, 30, 60, 60);
                g2.fillRect(30+2, 30+2, 60-4, 60-4);
            } else if (shapeNo == 2){
                int[] x = new int[3]; x[0]=30; x[1]=30; x[2]=30+60;
                int[] y = new int[3]; y[0]=30; y[1]=30+60; y[2]=30+60;
                g2.fillPolygon(x, y, 3);
            } else if (shapeNo == 3){
                int[] x = new int[3]; x[0]=30+60; x[1]=30; x[2]=30+60;
                int[] y = new int[3]; y[0]=30; y[1]=30+60; y[2]=30+60;
                g2.fillPolygon(x, y, 3);
            }
        }
        else if (panel.getGameState() == GameState.GAMEOVER){
            g2.setColor(Color.white);
            g2.drawOval(28, 20, 60, 60);
            g2.setFont(new Font("Courier", Font.BOLD, 15));
            g2.drawString("X", 45, 45);
            g2.drawString("X", 65, 45);
            g2.drawString("___", 45, 60);
        }
        repaint();

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