package views;

import models.Node;
import views.Center.MainCenterPanel;
import views.dashboard.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class MainPanel extends JPanel {

    private Dashboard dashboard;
    private MainCenterPanel mainCenterPanel;


    public MainPanel(ActionListener actionListener, MouseListener mouseListener) {
        setLayout(new BorderLayout());
        initComponents(mouseListener, actionListener);
    }

    private void initComponents(MouseListener mouseListener, ActionListener actionListener) {

        mainCenterPanel = new MainCenterPanel(mouseListener, actionListener);
        add(mainCenterPanel, BorderLayout.CENTER);

        dashboard = new Dashboard(actionListener);
        add(dashboard, BorderLayout.WEST);

    }

    public void setNodeRoot(Node nodeRoot) {
        mainCenterPanel.setNodeRootProperties(nodeRoot);
    }

    public void addElementToRoot(Node node) {
        mainCenterPanel.addElementToRoot(node);
    }

    public void showPopMenu(Component component, int x, int y, int idPopMenu, int idPanel) {
        mainCenterPanel.showPopMenu(component, x, y, idPopMenu, idPanel);
    }

    public String getSelectNameNode() {
        return mainCenterPanel.getSelectTypeNode();
    }

    public void addElementToNode(Node node) {
        mainCenterPanel.addElementToNode(node);
    }

    public String getIdSelectNode() {
        return mainCenterPanel.getIdSelectNode();
    }

    public void showUserPanel() {
        mainCenterPanel.showUserPanel();
    }

    public void showProperties() {
        mainCenterPanel.showProperties();
    }

    public int getHasCodeUserPanel() {
        return mainCenterPanel.getHasCodeUserPanel();
    }

    public boolean isShowingUsersPanel() {
        return mainCenterPanel.isShowingUserPanel();
    }

    public boolean isShowingPropertiesPanel() {
        return mainCenterPanel.isShowingPropertiesPanel();
    }

    public void addElementToRootUser(Node user) {
        mainCenterPanel.addElementToRootUser(user);
    }

    public String getIdSelectNodeUsers() {
        return mainCenterPanel.getIdSelectNodeUsers();
    }

    public void addElementToNodeUsers(Node node) {
        mainCenterPanel.addElementToNodeUsers(node);
    }

    public void showButtonAdd(boolean b) {
        mainCenterPanel.showButtonAdd(b);
    }

    public void setActionCommandAddButton() {
        mainCenterPanel.setActionCommandAddButton();
    }

    public void removeElementToTree() {
        mainCenterPanel.removeElementToTree();
    }

    public void removeElementToTreeUsers() {
        mainCenterPanel.removeElementToTreeUsers();
    }
}
