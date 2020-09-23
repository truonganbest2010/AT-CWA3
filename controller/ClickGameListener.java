package controller;

import view.ClickGamePanel;
import view.ClickGamePanel.GameState;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

import model.Circle;
import model.LeftSideTriangle;
import model.Line;
import model.Rectangle;
import model.RightSideTriangle;
import model.Shape;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class ClickGameListener implements MouseListener, ActionListener{
    
    private ClickGamePanel panel;
    private float interval;
    private int delay;
    private int period;
    private boolean picked;
    private int click;
    private Timer timer;
    private Random random = new Random();
    private int timeBarAnimation;
    
    private Color color;

    private int score;

    public ClickGameListener(ClickGamePanel panel){
        this.panel = panel;
        delay = 10;
        period = 100;
        interval = 1;
        score = 0;
        picked = false;
        click = 0;
        timeBarAnimation = random.nextInt(2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JButton button = (JButton) e.getSource();

        /* Exit */
        if (button == panel.getExitBtn()){
            System.exit(0);
        }
        /* New Game */
        else if (button == panel.getNewGameBtn()){
            panel.getNewGameBtn().setEnabled(false); /** Disable NewGame Button */
            panel.getQuestCanvas().setBackground(Color.black); /** Set game canvas background to black color */
            score = 0; /** Reset score */
            click = 0; /** Reset click count */
            panel.getGameCanvas().gridGenerator(); /** Initialize the new Color Grid once NewGame Button is clicked */
            panel.getScoreLabel().setText("" + score); /** Set score label back to zero */
            panel.getScoreLabel().setForeground(Color.black); /** Set score label color back to black */
            panel.setGameState(GameState.PLAYING); /** The Game Canvas starts to show the new Color Grid */
            timer = new Timer(); /** Create new Timer */
                

            timer.scheduleAtFixedRate(new TimerTask(){ /** Timer at a fixed rate to make Grid Color fade away */
                public void run(){
                        ArrayList<Shape> shapes = panel.getGameCanvas().getShapes(); /** get shapes from Game Canvas */
                        ArrayList<Shape> timeBar = panel.getQuestCanvas().getTimeBar(); /** get timeBar from Quest Canvas */
                        shapes.clear(); /** Initialize shapes list */
                        timeBar.clear(); /** Initialize timeBar */
                        
                        /** Time Bar in Quest Canvas */
                        if (timeBarAnimation == 0){
                            timeBar.add(new Line(5, panel.getQuestCanvas().getHeight()-5, Color.white, 5, 5+(int)(interval))); /** Left */
                            timeBar.add(new Line(panel.getQuestCanvas().getWidth()-5, 5, Color.white, panel.getQuestCanvas().getWidth()-5, panel.getQuestCanvas().getHeight()-5-(int)(interval))); /** Right */
                            timeBar.add(new Line(5, 5, Color.white, panel.getQuestCanvas().getWidth()-(int)(5+interval), 5)); /** Top */
                            timeBar.add(new Line(panel.getQuestCanvas().getWidth()-5, panel.getQuestCanvas().getHeight()-5, Color.white, 5+(int)(interval), panel.getQuestCanvas().getHeight()-5)); /** Bottom */
                        }
                        else
                        {
                            timeBar.add(new Line(5, panel.getQuestCanvas().getHeight()-5, Color.white, 5, 5+(int)(interval))); /** Left */
                            timeBar.add(new Line(panel.getQuestCanvas().getWidth()-5, 5, Color.white, panel.getQuestCanvas().getWidth()-5, panel.getQuestCanvas().getHeight()-5-(int)(interval))); /** Right */
                            timeBar.add(new Line(panel.getQuestCanvas().getWidth()-5, 5, Color.white, 5+(int)(interval), 5)); /** Top */
                            timeBar.add(new Line(5, panel.getQuestCanvas().getHeight()-5, Color.white, panel.getQuestCanvas().getWidth()-5-(int)(interval), panel.getQuestCanvas().getHeight()-5)); /** Bottom */
                        }

                        /** Refresh shape and color in main Grid */
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
                                /** Color Fade */
                                int r = color.getRed();
                                int g = color.getGreen();
                                int b = color.getBlue();
                                Color colorFade = new Color(r, g, b, 255-(int)(interval*2.4));

                                if (panel.getGameCanvas().getShapeGrid()[row][col] == 0){ /* Add Circle */
                                    shapes.add(new Circle(x1+10, y1+10, colorFade, y2-y1-20));
                                } else if (panel.getGameCanvas().getShapeGrid()[row][col] == 1){ /* Add Square */
                                    shapes.add(new Rectangle(x1+10, y1+10, colorFade, x2-x1-20, y2-y1-20));
                                } else if (panel.getGameCanvas().getShapeGrid()[row][col] == 2){
                                    shapes.add(new LeftSideTriangle(x1+10, y1+10, colorFade, panel.getPrefferedShapeSize()-20)); /** Add Left Side Triangle */
                                } else if (panel.getGameCanvas().getShapeGrid()[row][col] == 3){
                                    shapes.add(new RightSideTriangle(x1+10, y1+10, colorFade, panel.getPrefferedShapeSize()-20)); /** Add Right Sode */
                                }
                                
                                /** Find if the quest is picked up */
                                if (panel.getGameCanvas().getColorGrid()[row][col] == Color.black){
                                    picked = true;
                                }
                            }
                        }
                        
                        panel.getGameCanvas().repaint();
                        panel.getQuestCanvas().repaint();

                        /** Set up difficulty by adjusting interval */
                        if (interval < 90){
                            if (score < 5){
                                interval+=2.60;
                            } else if (score >= 5 && score < 10){
                                interval+=2.70;
                            } else if (score >= 10 && score < 20){
                                interval+=2.75;
                            } else if (score >= 20) {
                                interval+=2.9;
                            }
                        }
                        else if (interval >= 90){
                            /** GAME CONTINUE if the right shape is picked up */
                            if (picked){
                                interval = 1;
                                click = 0;
                                picked = false;
                                timeBarAnimation = random.nextInt(2);
                                panel.getQuestCanvas().setBackground(Color.black);
                                panel.getGameCanvas().gridGenerator();
                            } else {
                                panel.getGameCanvas().getShapes().clear();
                                panel.getQuestCanvas().getTimeBar().clear();
                                /** GAME OVER if nothing is picked up */
                                panel.setGameState(GameState.GAMEOVER); /** Stop showing color grid on game canvas and show GAME OVER signal */
                                panel.getQuestCanvas().setBackground(new Color(255, 51, 51, 96));
                                panel.getGameCanvas().setScore(score);
                                panel.getGameCanvas().repaint();
                                panel.getQuestCanvas().repaint();
                                panel.getScoreLabel().setForeground(new Color(255, 150, 150));
                                panel.getNewGameBtn().setEnabled(true);
                                interval = 1;
                                period = 100;
                                score = 0;
                            }     
                        }                   
                        // System.out.println(interval);

                    /** If GAME OVER, cancel the current timer */
                    if (panel.getGameState() == GameState.GAMEOVER){
                        timer.cancel();
                        timer.purge();
                        score = 0;
                    }                   
                }
            }, delay, period);

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
                    panel.getQuestCanvas().setBackground(new Color(20, 255, 20, 100));
                    panel.getScoreLabel().setFont(new Font("Courier", Font.BOLD, 40));
                    panel.getScoreLabel().setText("" + score);
                    panel.getGameCanvas().setColorGrid(row, col, Color.black);
                    click++;
                    
            } else {
                panel.getGameCanvas().getShapes().clear();
                panel.getQuestCanvas().getTimeBar().clear();

                panel.setGameState(GameState.GAMEOVER);
                panel.getGameCanvas().setScore(score);
                panel.getGameCanvas().repaint();
                panel.getQuestCanvas().setBackground(new Color(255, 51, 51, 96));
                panel.getNewGameBtn().setEnabled(true);
                panel.getScoreLabel().setForeground(new Color(255, 150, 150));
                interval = 1;
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