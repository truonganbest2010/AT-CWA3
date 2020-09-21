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
    private ClickGameCanvas gameCanvas;
    private QuestCanvas questCanvas;
    private JLabel scoreLabel = new JLabel();

    private GameState gameState = GameState.READY;

    public ClickGamePanel(JFrame window){
        this.window = window;
        scoreLabel.setFont(new Font("Courier", Font.BOLD, 40));
        scoreLabel.setText("0");
    }

    public void init(){
        Container cp = window.getContentPane();
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 1));
        cp.add(BorderLayout.EAST, rightPanel);

        questCanvas = new QuestCanvas(this);
        newGameBtn = new JButton("New Game");

        JPanel questPanel = new JPanel();
        TitledBorder questTitle = BorderFactory.createTitledBorder("QUEST");
        questPanel.setBorder(questTitle);
        questPanel.setLayout(new GridLayout(2, 1));
        questPanel.add(questCanvas);
            JPanel scorePanel = new JPanel();
            TitledBorder scoreBorder = BorderFactory.createTitledBorder("SCORE");
            scorePanel.setBorder(scoreBorder);
            scorePanel.add(scoreLabel);
        questPanel.add(scorePanel);

        rightPanel.add(questPanel);
        rightPanel.add(newGameBtn);

        JPanel centralPanel = new JPanel();
        cp.add(BorderLayout.CENTER, centralPanel);
        TitledBorder gameCanvasTitle = BorderFactory.createTitledBorder("");
        centralPanel.setBorder(gameCanvasTitle);

        gameCanvas = new ClickGameCanvas(this, 5, 5, 100);
        centralPanel.add(gameCanvas);


        ClickGameListener listener = new ClickGameListener(this);
        newGameBtn.addActionListener(listener);
        gameCanvas.addMouseListener(listener);
    }

    public JButton getNewGameBtn(){
        return newGameBtn;
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