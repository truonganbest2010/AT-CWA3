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
    private Color[][] colorGrid;
    private int[][] shapeGrid; // 1 is rectangle, 0 is circle

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

    }

    public void gridGenerator(){
        Random r = new Random();
        for (int i = 0; i < gridRows; i++){
            for (int j = 0; j < gridCols; j++){
                colorGrid[i][j] = new Color((int)(r.nextInt(255)),
                                            (int)(r.nextInt(255)),
                                            (int)(r.nextInt(255)));
                shapeGrid[i][j] = r.nextInt(2);
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
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Courier", Font.BOLD, 30));
            g2.drawString("Color Run", 150, 200);
            g2.drawString("Click <New Game> to Start", 30, 250);
            repaint();
        }

        else if (panel.getGameState() == GameState.PLAYING){
            for (var s: shapes){
                s.render(g2);
            }
        }

        else if (panel.getGameState() == GameState.GAMEOVER){
            shapes.clear();
            g2.setColor(Color.RED);
            g2.setFont(new Font("Courier", Font.BOLD, 50));
            g2.drawString("Game Over", 150, 250);
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

    public void setColorBlack(int row, int col){
        colorGrid[row][col] = Color.black;
    }

}