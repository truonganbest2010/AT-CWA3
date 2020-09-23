package view;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.ClickGameListener;

import java.awt.*;

public class ClickGamePanel {
    
    public enum GameState {
        READY, PLAYING, GAMEOVER
    }
    private JFrame window;

    private JButton newGameBtn;
    private JButton exitBtn;

    private int prefferedShapeSize;
    private int gameSize;

    private ClickGameCanvas gameCanvas;
    private QuestCanvas questCanvas;
    private JLabel scoreLabel = new JLabel();

    private GameState gameState = GameState.READY;

    public ClickGamePanel(JFrame window){
        this.window = window;
        scoreLabel.setFont(new Font("Courier", Font.BOLD, 40));
        scoreLabel.setText("0");
        gameSize = 6;
        prefferedShapeSize = 100;
    }

    public void init(){
        Font font = new Font("Courier", Font.BOLD, 16);

        Container cp = window.getContentPane();
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 1));
        cp.add(BorderLayout.EAST, rightPanel);

            
            JPanel questPanel = new JPanel();
            TitledBorder questTitle = BorderFactory.createTitledBorder("QUEST");
            questTitle.setTitleFont(font);
            questPanel.setBorder(questTitle);
            questPanel.setPreferredSize(new Dimension(100, 100));
            questCanvas = new QuestCanvas(this);
            questPanel.add(questCanvas);
        rightPanel.add(questPanel);

                JPanel scorePanel = new JPanel();
                TitledBorder scoreBorder = BorderFactory.createTitledBorder("SCORE");
                scoreBorder.setTitleFont(font);
                scorePanel.setBorder(scoreBorder);
                scorePanel.add(scoreLabel);
        
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(3, 1));
        rightPanel.add(buttonPanel);

            newGameBtn = new JButton("New Game");
            newGameBtn.setFont(font);
            exitBtn = new JButton("Exit");
            exitBtn.setFont(font);
        buttonPanel.add(scorePanel);
        buttonPanel.add(newGameBtn);
        buttonPanel.add(exitBtn);
            

        JPanel centralPanel = new JPanel();
        cp.add(BorderLayout.CENTER, centralPanel);
        TitledBorder gameCanvasTitle = BorderFactory.createTitledBorder("");
        centralPanel.setBorder(gameCanvasTitle);

        gameCanvas = new ClickGameCanvas(this, gameSize, gameSize, prefferedShapeSize);
        centralPanel.add(gameCanvas);


        ClickGameListener listener = new ClickGameListener(this);
        newGameBtn.addActionListener(listener);
        gameCanvas.addMouseListener(listener);
        exitBtn.addActionListener(listener);
        
    }

    public int getPrefferedShapeSize(){
        return prefferedShapeSize;
    }

    public JButton getNewGameBtn(){
        return newGameBtn;
    }

    public JButton getExitBtn(){
        return exitBtn;
    }

    public JLabel getScoreLabel(){
        return scoreLabel;
    }

    public ClickGameCanvas getGameCanvas(){
        return gameCanvas;
    }

    public QuestCanvas getQuestCanvas(){
        return questCanvas;
    }

    public JFrame getWindow(){
        return window;
    }

    public GameState getGameState(){
        return gameState;
    }

    public void setGameState(GameState state){
        this.gameState = state;
    }
    
}