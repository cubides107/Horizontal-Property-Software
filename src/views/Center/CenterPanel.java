package views.Center;

import models.NodeTreeViews;
import models.TypeFiles;
import org.w3c.dom.Node;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class CenterPanel extends JPanel {

    public static final String PROPERTIES_PANEL = "propertiesPanel";
    public static final String USERS_PANEL = "users_Panel";
    private CardLayout cardLayout;

    private PropertiesPanel propertiesPanel;
    private UsersPanel usersPanel;

    public CenterPanel(MouseListener mouseListener, ActionListener actionListener) {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        initComponents(mouseListener, actionListener);
        cardLayout.show(this, PROPERTIES_PANEL);
    }

    private void initComponents(MouseListener mouseListener, ActionListener actionListener) {
        propertiesPanel = new PropertiesPanel(mouseListener, actionListener);
        add(propertiesPanel, PROPERTIES_PANEL);

        usersPanel = new UsersPanel(mouseListener, actionListener);
        add(usersPanel, USERS_PANEL);

    }

    public void showPropertiesPanel() {
        cardLayout.show(this, PROPERTIES_PANEL);
    }

    public void showUsersPanel() {
        cardLayout.show(this, USERS_PANEL);
    }

    public void setNodeRoot(NodeTreeViews nodeRoot) {
        propertiesPanel.setNodeRoot(nodeRoot);
        usersPanel.setNodeRoot(new NodeTreeViews(TypeFiles.HORIZONTAL_PROPERTY_USER, nodeRoot.getName(), nodeRoot.getID()));
    }

    public String getSelectTypeNode() {
        if (propertiesPanel.getSelectTypeNode().equals(" ")) {
            return usersPanel.getSelectTypeNode();
        } else {
            return propertiesPanel.getSelectTypeNode();
        }
    }

    public String getIdSelectNode() {
        return propertiesPanel.getIdSelectNode();
    }

    public DefaultMutableTreeNode getSelectNode() {
        return propertiesPanel.getSelectNode();
    }

    public void addElementToRoot(NodeTreeViews node) {
        propertiesPanel.addElementToRoot(node);
    }

    public void addElementToNode(NodeTreeViews node) {
        propertiesPanel.addElementToNode(node);
    }

    public void showPopMenu(Component component, int x, int y, int idPopMenu, int idPanel) {
        switch (idPanel) {
            case 0:
                usersPanel.showPopMenu(component, x, y, 0);
                break;
            case 1:
                propertiesPanel.showPopMenu(component, x, y, idPopMenu);
                break;
        }
    }

    public int getHasCodeUserPanel() {
        return usersPanel.hashCode();
    }

    public boolean isShowingUserPanel() {
        return usersPanel.isShowing();
    }

    public boolean isShowingPropertiesPanel() {
        return propertiesPanel.isShowing();
    }

    public void addElementToRootUser(NodeTreeViews user) {
        usersPanel.addElementToRoot(user);
    }

    public String getIdSelectNodeUsers() {
        return usersPanel.getIdSelectNode();
    }

    public void addElementToNodeUsers(NodeTreeViews node) {
        usersPanel.addElementToNode(node);
    }

    public void showButtonAdd(boolean b) {
        propertiesPanel.showButtonAdd(b);
    }

    public void setActionCommandAddButton() {
        propertiesPanel.setActionCommandAddButton();
    }

    public void removeElementToTree() {
        propertiesPanel.removeElementToTree();
    }

    public void removeElementToTreeUsers() {
        usersPanel.removeElementToTreeUsers();
    }

    public void removeElementToTreeUsersById(int idSelectNode) {
        usersPanel.removeElementToTreeUsersById(idSelectNode);
    }

    public void loadDataProperties(Node root) {
        propertiesPanel.loadDataProperties(root);
    }

    public void resetCommandButtonAdd() {
        propertiesPanel.resetCommandButtonAdd();
    }
}
