import java.awt.Font;

import javax.swing.JFrame;

import view.ClickGamePanel;

public class Main{

    public static void main(String[] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocation(400, 100);
        window.setTitle("Click Game");
        window.setFont(new Font("Courier", Font.BOLD, 12));

        ClickGamePanel clickGamePanel = new ClickGamePanel(window);
        clickGamePanel.init();

        window.pack();
        window.setVisible(true);
    }
}