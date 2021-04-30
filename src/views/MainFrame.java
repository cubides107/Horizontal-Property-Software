package views;

import views.login.MainLogin;

import javax.swing.*;
import java.awt.event.ActionListener;


public class MainFrame extends JFrame {

    public static final int WIDTH_FRAME = 1000;
    public static final int HEIGHT_FRAME = 900;

    private MainPanel mainPanel;
    private MainLogin mainLogin;

    public MainFrame(ActionListener actionListener) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setLocationRelativeTo(null);

        mainPanel = new MainPanel(actionListener);
        add(mainPanel);

        mainLogin = new MainLogin(actionListener);
        setVisible(true);

    }

    public void showLogin(boolean option){
        mainLogin.setVisible(option);
    }

    public String[] getDataUser(){
         return mainLogin.getDataUser();
    }
}
