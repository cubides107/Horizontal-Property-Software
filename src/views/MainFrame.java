package views;

import models.Node;
import views.login.MainLogin;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;


public class MainFrame extends JFrame {

    public static final int WIDTH_FRAME = 1000;
    public static final int HEIGHT_FRAME = 900;

    private MainPanel mainPanel;
    private MainLogin mainLogin;

    public MainFrame(ActionListener actionListener, MouseListener mouseListener) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setLocationRelativeTo(null);

        mainPanel = new MainPanel(actionListener, mouseListener);
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

    public void setNodeRoot(Node nodeRoot){
       mainPanel.setNodeRoot(nodeRoot);
    }

    public  void addElementToRoot(Node node){
        mainPanel.addElementToRoot(node);
    }

    public void showPopMenu(Component component, int x, int y, int idPopMenu) {
        mainPanel.showPopMenu(component,x,y,idPopMenu);
    }

    public String getSelectNameNode() {
        return mainPanel.getSelectNameNode();
    }

    public String getIdSelectNode() {
        return mainPanel.getIdSelectNode();
    }

    public void addElementToNode(Node node) {
       mainPanel.addElementToNode(node);
    }
}
