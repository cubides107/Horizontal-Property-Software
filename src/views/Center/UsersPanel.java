package views.Center;

import Presenter.Events;
import models.Node;
import views.MenuItemModel;
import views.TreeCellRenderer;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class UsersPanel extends JPanel {

    private DefaultTreeModel modelTree;
    private DefaultMutableTreeNode nodeRoot;
    private JTree tree;
    JPopupMenu popMenuRoot;
    JPopupMenu popMenuUsers;
    JPopupMenu deletePropertyOnly;


    public UsersPanel(MouseListener mouseListener, ActionListener actionListener) {
        popMenuRoot = new JPopupMenu();
        popMenuUsers = new JPopupMenu();
        deletePropertyOnly = new JPopupMenu();
        setLayout(new BorderLayout());
        setBackground(Color.decode("#1C2868"));
        nodeRoot = new DefaultMutableTreeNode();
        modelTree = new DefaultTreeModel(nodeRoot);
        initComponents(mouseListener, actionListener);
    }

    private void initComponents(MouseListener mouseListener, ActionListener actionListener) {
        JMenu menu = new JMenu("Agregar");
        MenuItemModel removeElement = new MenuItemModel("Eliminar", actionListener, Events.DELETE_PROPERTY.name());
        menu.add(new MenuItemModel("Usuario", actionListener, Events.ADD_USERS_POP_MENU.name()));
        popMenuRoot.add(menu);


        JMenu menuUsers = new JMenu("Agregar Nueva Propiedad");
        menuUsers.add(new MenuItemModel("Casa", actionListener, Events.ADD_HOUSE_USER.name()));
        menuUsers.add(new MenuItemModel("Apartamento", actionListener, Events.ADD_APARTMENT_USER.name()));
        popMenuUsers.add(menuUsers);
        popMenuUsers.add(new MenuItemModel("Seleccionar Propiedad", actionListener, Events.SELECT_PROPERTY_TO_USER.name()));
        popMenuUsers.add(removeElement);

        deletePropertyOnly.add(new MenuItemModel("Eliminar", actionListener, Events.DELETE_PROPERTY_TO_USER.name()));

        tree = new JTree(modelTree);
        tree.addMouseListener(mouseListener);
        tree.setCellRenderer(new TreeCellRenderer());
        tree.setShowsRootHandles(true);
        tree.setOpaque(false);
        add(tree, BorderLayout.CENTER);
    }

    public String getSelectTypeNode() {
        TreeSelectionModel treeSelectionModel = tree.getSelectionModel();
        if (treeSelectionModel.getSelectionPath() != null) {
            DefaultMutableTreeNode lastPathComponent = (DefaultMutableTreeNode) treeSelectionModel.getSelectionPath().getLastPathComponent();
            Node userObject = (Node) lastPathComponent.getUserObject();
            return userObject.getTypeFile().getType();
        } else {
            return " ";
        }
    }

    public String getIdSelectNode() {
        TreeSelectionModel treeSelectionModel = tree.getSelectionModel();
        if (treeSelectionModel.getSelectionPath() != null) {
            DefaultMutableTreeNode lastPathComponent = (DefaultMutableTreeNode) treeSelectionModel.getSelectionPath().getLastPathComponent();
            Node userObject = (Node) lastPathComponent.getUserObject();
            return userObject.getID();
        } else {
            return " ";
        }
    }

    public void showPopMenuDeletePropertyToUser(Component component, int x, int y) {
        deletePropertyOnly.show(component, x, y);
    }

    public DefaultMutableTreeNode getSelectNode() {
        TreeSelectionModel treeSelectionModel = tree.getSelectionModel();
        if (treeSelectionModel.getSelectionPath() != null) {
            return (DefaultMutableTreeNode) treeSelectionModel.getSelectionPath().getLastPathComponent();
        } else {
            return null;
        }
    }

    public void addElementToRoot(Node node) {
        this.nodeRoot.add(new DefaultMutableTreeNode(node));
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tree.updateUI();
    }

    public void addElementToNode(Node node) {
        if (getSelectNode() != null) {
            DefaultMutableTreeNode selectNode = getSelectNode();
            selectNode.add(new DefaultMutableTreeNode(node));
        }
        tree.updateUI();
    }

    public void showPopMenu(Component component, int x, int y, int idPopMenu) {
        if (idPopMenu == 0) {
            popMenuRoot.show(component, x, y);
        } else {
            popMenuUsers.show(component, x, y);
        }
    }

    public void setNodeRoot(Node nodeRoot) {
        this.nodeRoot.setUserObject(nodeRoot);
        tree.updateUI();
    }

    public void removeElementToTreeUsers() {
        modelTree.removeNodeFromParent(getSelectNode());
    }

    public void removeElementToTreeUsersById(int idSelectNode) {
        removeById(idSelectNode, (DefaultMutableTreeNode) modelTree.getRoot());
    }

    private void removeById(int idSelectNode, DefaultMutableTreeNode actual) {
        Node nodeAux = (Node) actual.getUserObject();
        if ((nodeAux.getID().equals((String.valueOf(idSelectNode))))) {
            modelTree.removeNodeFromParent(actual);
            return;
        }
        int childCount = actual.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (i != idSelectNode) {
                removeById(idSelectNode, (DefaultMutableTreeNode) modelTree.getChild(actual, i));
            }
        }
    }
}
