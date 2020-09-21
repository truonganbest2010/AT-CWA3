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
        
        if (panel.getGameState() == GameState.PLAYING){
            if (colorQuest != null){
                g2.setColor(colorQuest);
            }
            if (shapeNo == 0){
                g2.drawOval(30, 40, 40, 40);
                g2.fillOval(30+2, 40+2, 40-4, 40-4);
            } else if (shapeNo == 1){
                g2.drawRect(30, 40, 40, 40);
                g2.fillRect(30+2, 40+2, 40-4, 40-4);
            }
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