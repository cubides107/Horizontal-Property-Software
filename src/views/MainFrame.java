package views;

import models.NodeTreeViews;
import org.w3c.dom.Node;
import views.login.MainLogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.time.LocalDate;


public class MainFrame extends JFrame {

    public static final int WIDTH_FRAME = 1000;
    public static final int HEIGHT_FRAME = 900;

    private MainPanel mainPanel;
    private MainLogin mainLogin;
    private DialogReport dialogReport;

    public MainFrame(ActionListener actionListener, MouseListener mouseListener) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/IconApp1.png")).getImage());
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setLocationRelativeTo(null);

        mainPanel = new MainPanel(actionListener, mouseListener);
        add(mainPanel);

        mainLogin = new MainLogin(actionListener);

        dialogReport = new DialogReport(actionListener);
        setVisible(true);
    }

    public void showDialogReport(boolean option) {
        dialogReport.setVisible(option);
    }

    public LocalDate[] getDate(){
        return dialogReport.getDate();
    }

    public void showLogin(boolean option){
        mainLogin.setVisible(option);
    }

    public String[] getDataUser(){
         return mainLogin.getDataUser();
    }

    public void setNodeRoot(NodeTreeViews nodeRoot){
       mainPanel.setNodeRoot(nodeRoot);
    }

    public  void addElementToRoot(NodeTreeViews node){
        mainPanel.addElementToRoot(node);
    }

    public void showPopMenu(Component component, int x, int y, int idPopMenu, int idPanel) {
        mainPanel.showPopMenu(component,x,y,idPopMenu, idPanel);
    }

    public String getSelectNameNode() {
        return mainPanel.getSelectNameNode();
    }

    public String getIdSelectNodeProperties() {
        return mainPanel.getIdSelectNode();
    }

    public void addElementToNode(NodeTreeViews node) {
       mainPanel.addElementToNode(node);
    }

    public void showUserPanel() {
        mainPanel.showUserPanel();
    }

    public void showPropertiesPanel() {
        mainPanel.showProperties();
    }

    public int getHasCodeUsersPanel() {
        return mainPanel.getHasCodeUserPanel();
    }

    public boolean isShowingUsersPanel() {
        return mainPanel.isShowingUsersPanel();
    }

    public boolean isShowingPropertiesPanel() {
        return mainPanel.isShowingPropertiesPanel();
    }

    public void addElementToRootUser(NodeTreeViews user) {
        mainPanel.addElementToRootUser(user);
    }

    public String getIdSelectNodeUsers() {
        return mainPanel.getIdSelectNodeUsers();
    }

    public void addElementToNodeUsers(NodeTreeViews node) {
        mainPanel.addElementToNodeUsers(node);
    }

    public void showButtonAdd(boolean b) {
        mainPanel.showButtonAdd(b);
    }

    public void setActionCommandAddButton() {
        mainPanel.setActionCommandAddButton();
    }

    public void removeElementToTreeProperties() {
        mainPanel.removeElementToTree();
    }

    public void removeElementToTreeUsers() {
        mainPanel.removeElementToTreeUsers();
    }

    public void removeElementToTreeUsersById(int idSelectNode) {
        mainPanel.removeElementToTreeUsersById(idSelectNode);
    }

    public void loadDataProperties(Node root) {
        mainPanel.loadDataProperties(root);
    }

    public void setResetCommandButtonAdd() {
        mainPanel.setResetCommandButtonAdd();
    }

    public void loadDataUsers(Node root) {
        mainPanel.loadDataUsers(root);
    }

    public void showTableReports() {
        mainPanel.showTableReports();
    }

    public void showReportUsers() {
        mainPanel.showReportUsers();
    }

    public void repaintNodes(String idNodes) {
        mainPanel.repaintNodes(idNodes);
    }
}
