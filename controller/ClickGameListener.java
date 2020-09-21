package controller;

import view.ClickGamePanel;
import view.ClickGamePanel.GameState;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import model.Circle;
import model.Rectangle;
import model.Shape;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class ClickGameListener implements MouseListener, ActionListener{
    
    private ClickGamePanel panel;
    private int interval;
    private int delay;
    private int period;
    private boolean picked;
    private int click;
    
    private Color color;

    private int score;

    public ClickGameListener(ClickGamePanel panel){
        this.panel = panel;
        delay = 10;
        period = 100;
        interval = 0;
        score = 0;
        picked = false;
        click = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton button = (JButton) e.getSource();

        if (button == panel.getNewGameBtn()){
            panel.getNewGameBtn().setEnabled(false);
            score = 0;
            click = 0;
            panel.getGameCanvas().gridGenerator();
            panel.getScoreLabel().setText("" + score);
            panel.setGameState(GameState.PLAYING);
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask(){
                public void run(){
                    
                        ArrayList<Shape> shapes = panel.getGameCanvas().getShapes();
                        shapes.clear();
                        int row, col;
                        picked = false;
                        double cellWidth = (double)panel.getGameCanvas().getWidth() / panel.getGameCanvas().getGridCols();
                        double cellHeight = (double)panel.getGameCanvas().getHeight() / panel.getGameCanvas().getGridRows();

                        for (row = 0; row < panel.getGameCanvas().getGridRows(); row++){
                            for (col = 0 ; col < panel.getGameCanvas().getGridCols(); col++){
                                int x1 = (int)(col*cellWidth);
                                int y1 = (int)(row*cellHeight);
                                int x2 = (int)((col+1)*cellWidth);
                                int y2 = (int)((row+1)*cellHeight);

                                if (panel.getGameCanvas().getColorGrid()[row][col] != null){
                                    color = panel.getGameCanvas().getColorGrid()[row][col];
                                }

                                if (panel.getGameCanvas().getShapeGrid()[row][col] == 0){
                                    shapes.add(new Circle(x1+5, y1+5, color, y2-y1-10-interval));
                                } else if (panel.getGameCanvas().getShapeGrid()[row][col] == 1){
                                    shapes.add(new Rectangle(x1+5, y1+5, color, x2-x1-10-interval, y2-y1-10-interval));
                                }
                                
                                if (panel.getGameCanvas().getColorGrid()[row][col] == Color.black){
                                    picked = true;
                                }
                            }
                        }
                        panel.getGameCanvas().repaint();

                        if (interval < 50)
                            interval++;
                        if (interval == 50){
                            if (picked){
                                interval = 0;
                                click = 0;
                                panel.getGameCanvas().gridGenerator();
                            } else {
                                panel.getGameCanvas().getShapes().clear();
                                panel.setGameState(GameState.GAMEOVER);
                                panel.getGameCanvas().setScore(score);
                                panel.getGameCanvas().repaint();
                                panel.getNewGameBtn().setEnabled(true);
                                interval = 0;
                                period = 100;
                                score = 0;
                            }     
                        }
                        if (score%2 == 0 && period >= 30){
                            period-= 10;
                        }
                        System.out.println(interval);


                    if (panel.getGameState() == GameState.GAMEOVER){
                        timer.cancel();
                        timer.purge();
                        score = 0;
                    }
                    
                }
            }, delay, period);

            System.out.println(panel.getNewGameBtn().getText());
        }


    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        click++;
		if (panel.getGameState() == GameState.PLAYING && click == 1){
            int row = panel.getGameCanvas().findRow(e.getY());
            int col = panel.getGameCanvas().findColumn(e.getX());

            if (panel.getGameCanvas().getColorGrid()[row][col] == panel.getQuestCanvas().getColorQuest() &&
                panel.getGameCanvas().getShapeGrid()[row][col] == panel.getQuestCanvas().getShapeNo()
                ){
                    score++;
                    panel.getScoreLabel().setFont(new Font("Courier", Font.BOLD, 40));
                    panel.getScoreLabel().setText("" + score);
                    panel.getGameCanvas().setColorBlack(row, col);
                    click++;
                    
            } else {
                panel.getGameCanvas().getShapes().clear();
                panel.setGameState(GameState.GAMEOVER);
                panel.getGameCanvas().setScore(score);
                panel.getGameCanvas().repaint();
                panel.getNewGameBtn().setEnabled(true);
                interval = 0;
                period = 100;
                score = 0;
                click = 0;
            }
        }
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

    

}