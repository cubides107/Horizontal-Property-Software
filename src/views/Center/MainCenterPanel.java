package views.Center;

import models.NodeTreeViews;
import org.w3c.dom.Node;
import views.header.MainHeader;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class MainCenterPanel extends JPanel {

    private MainHeader mainHeader;
    private CenterPanel centerPanel;

    public MainCenterPanel(MouseListener mouseListener, ActionListener actionListener) {
        setLayout(new BorderLayout());
        initComponents(mouseListener, actionListener);
    }

    private void initComponents(MouseListener mouseListener, ActionListener actionListener) {
        mainHeader = new MainHeader();
        add(mainHeader, BorderLayout.NORTH);

        centerPanel = new CenterPanel(mouseListener, actionListener);
        add(centerPanel, BorderLayout.CENTER);
    }

    public void setNodeRootProperties(NodeTreeViews nodeRoot) {
        centerPanel.setNodeRoot(nodeRoot);
    }

    public String getSelectTypeNode() {
            return centerPanel.getSelectTypeNode();

    }

    public String getIdSelectNode() {
        return centerPanel.getIdSelectNode();
    }

    public DefaultMutableTreeNode getSelectNode() {
        return centerPanel.getSelectNode();
    }

    public void addElementToRoot(NodeTreeViews node) {
        centerPanel.addElementToRoot(node);
    }

    public void addElementToNode(NodeTreeViews node) {
        centerPanel.addElementToNode(node);
    }

    public void showPopMenu(Component component, int x, int y, int idPopMenu, int idPanel) {
        centerPanel.showPopMenu(component, x, y, idPopMenu,idPanel);
    }


    public void showUserPanel() {
        centerPanel.showUsersPanel();
    }

    public void showProperties() {
        centerPanel.showPropertiesPanel();
    }

    public int getHasCodeUserPanel() {
        return centerPanel.getHasCodeUserPanel();
    }

    public boolean isShowingUserPanel() {
        return centerPanel.isShowingUserPanel();
    }

    public boolean isShowingPropertiesPanel() {
        return centerPanel.isShowingPropertiesPanel();
    }

    public void addElementToRootUser(NodeTreeViews user) {
        centerPanel.addElementToRootUser(user);
    }

    public String getIdSelectNodeUsers() {
       return centerPanel.getIdSelectNodeUsers();
    }

    public void addElementToNodeUsers(NodeTreeViews node) {
        centerPanel.addElementToNodeUsers(node);
    }

    public void showButtonAdd(boolean b) {
        centerPanel.showButtonAdd(b);
    }

    public void setActionCommandAddButton() {
        centerPanel.setActionCommandAddButton();
    }

    public void removeElementToTree() {
        centerPanel.removeElementToTree();
    }

    public void removeElementToTreeUsers() {
        centerPanel.removeElementToTreeUsers();
    }

    public void removeElementToTreeUsersById(int idSelectNode) {
        centerPanel.removeElementToTreeUsersById(idSelectNode);
    }

    public void loadDataProperties(Node root) {
        centerPanel.loadDataProperties(root);
    }

    public void setResetCommandButtonAdd() {
        centerPanel.resetCommandButtonAdd();
    }
}
