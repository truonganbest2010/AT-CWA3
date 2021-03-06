package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

import model.Shape;
import view.ClickGamePanel.GameState;

public class ClickGameCanvas extends JPanel {
    
    private ClickGamePanel panel;
    private int gridRows;
    private int gridCols;
    private Color lineColor;
    private Color[][] colorGrid;
    private int[][] shapeGrid; // 3 is right side triangle, 2 is left side triangle, 1 is square, 0 is circle
    private int score;

    private ArrayList<Shape> shapes = new ArrayList<>();

    public ClickGameCanvas(ClickGamePanel panel, int rows, int cols, int preferredSquareSize){
        this.panel = panel;
        gridCols = cols;
        gridRows = rows;
        colorGrid = new Color[gridRows][gridCols];
        shapeGrid = new int[gridRows][gridCols];
        setPreferredSize(new Dimension(preferredSquareSize*gridCols,
                    preferredSquareSize*gridRows));
        gridGenerator();
        setBackground(Color.black);
        lineColor = new Color(255, 255, 255, 96);

    }

    public void gridGenerator(){
        Random r = new Random();
        for (int i = 0; i < gridRows; i++){
            for (int j = 0; j < gridCols; j++){
                colorGrid[i][j] = new Color((int)(40+Math.random()*214),
                                            (int)(40+Math.random()*214),
                                            (int)(40+Math.random()*214));
                shapeGrid[i][j] = r.nextInt(4);
            }
        }

        int col, row;
        col = r.nextInt(gridCols-1);
        row = r.nextInt(gridRows-1);
        panel.getQuestCanvas().setShapeNo(shapeGrid[row][col]);
        panel.getQuestCanvas().setColorQuest(colorGrid[row][col]);
    }

    public int findRow(int pixelY){
        return (int)(((double)pixelY)/getHeight()*gridRows);
    }

    public int findColumn(int pixelX) {
		return (int)(((double)pixelX)/getWidth()*gridCols);
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        super.paintComponent(g);

        if (panel.getGameState() == GameState.READY){
            setBackground(Color.BLACK);
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Courier", Font.BOLD, 30));
            g2.drawString("Color Run", getWidth()/3, getHeight()/3);
            g2.drawString("Click <New Game> to Start", getWidth()/7 - getWidth()/14, getHeight()/3 + getHeight()/5);
            repaint();
        }

        else if (panel.getGameState() == GameState.PLAYING){
            setBackground(Color.BLACK);
            for (var s: shapes){
                s.render(g2);
            }

            int row, col;
            double cellWidth = (double)getWidth() / gridCols;
		    double cellHeight = (double)getHeight() / gridRows;
            if (lineColor != null) {
                g.setColor(lineColor);
                for (row = 1; row < gridRows; row++) {
                    int y = (int)(row*cellHeight);
                    g.drawLine(0,y,getWidth(),y);
                }
                for (col = 1; col < gridRows; col++) {
                    int x = (int)(col*cellWidth);
                    g.drawLine(x,0,x,getHeight());
                }
            }
        }

        else if (panel.getGameState() == GameState.GAMEOVER){
            shapes.clear();
            setBackground(new Color(255, 51, 51, 96));
            g2.setColor(Color.white);
            g2.setFont(new Font("Courier", Font.BOLD, 45));
            g2.drawString("Game Over", getWidth()/4, getHeight()/3);
            g2.drawString("Highest Score: " + score, getWidth()/7 - getWidth()/20, getHeight()/3 + getHeight()/5);
            repaint();
        }

    }

    public int getGridCols(){
        return gridCols;
    }

    public int getGridRows(){
        return gridRows;
    }
    
    public Color[][] getColorGrid(){
        return colorGrid;
    }

    public int[][] getShapeGrid(){
        return shapeGrid;
    }

    public ArrayList<Shape> getShapes(){
        return shapes;
    }

    public void setColorGrid(int row, int col, Color color){
        colorGrid[row][col] = color;
    }

    public void setScore(int score){
        this.score = score;
    }

}