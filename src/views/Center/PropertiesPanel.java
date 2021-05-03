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

public class PropertiesPanel extends JPanel {

    private DefaultTreeModel model;
    private DefaultMutableTreeNode nodeRoot;
    private JTree tree;
    JPopupMenu popMenuRoot;
    JPopupMenu popMenuBuilding;

    public PropertiesPanel(MouseListener mouseListener, ActionListener actionListener) {
        popMenuRoot = new JPopupMenu();
        popMenuBuilding = new JPopupMenu();
        setLayout(new BorderLayout());
        setBackground(Color.decode("#1C2868"));
        nodeRoot = new DefaultMutableTreeNode();
        model = new DefaultTreeModel(nodeRoot);
        initComponents(mouseListener, actionListener);
        System.out.println("pROPIETARES" + hashCode());
    }

    public void setNodeRoot(Node nodeRoot) {
        this.nodeRoot.setUserObject(nodeRoot);
        tree.updateUI();
    }

    private void initComponents(MouseListener mouseListener, ActionListener actionListener) {

        JMenu menu = new JMenu("Agregar");
        MenuItemModel removeElement = new MenuItemModel("Eliminar", actionListener, Events.ADD_BUILDING.name());
        menu.add(new MenuItemModel("Edificio", actionListener, Events.ADD_BUILDING.name()));
        menu.add(new MenuItemModel("Casa", actionListener, Events.ADD_HOUSE.name()));
        menu.add(new MenuItemModel("Salon comunal ", actionListener, Events.ADD_BUILDING.name()));
        menu.add(new MenuItemModel("Piscina ", actionListener, Events.ADD_BUILDING.name()));
        menu.add(new MenuItemModel("Cancha", actionListener, Events.ADD_BUILDING.name()));
        popMenuRoot.add(menu);
        popMenuRoot.add(new MenuItemModel("Eliminar", actionListener, Events.ADD_BUILDING.name()));


        JMenu menuBuilding = new JMenu("Agregar");
        menuBuilding.add(new MenuItemModel("Apartamento", actionListener, Events.ADD_APARTMENT.name()));
        popMenuBuilding.add(menuBuilding);
        popMenuBuilding.add(removeElement);

        tree = new JTree(model);
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

    public String getIdSelectNode(){
        TreeSelectionModel treeSelectionModel = tree.getSelectionModel();
        if (treeSelectionModel.getSelectionPath() != null) {
            DefaultMutableTreeNode lastPathComponent = (DefaultMutableTreeNode) treeSelectionModel.getSelectionPath().getLastPathComponent();
            Node userObject = (Node) lastPathComponent.getUserObject();
            return userObject.getID();
        } else {
            return " ";
        }
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
            popMenuBuilding.show(component, x, y);
        }
    }
}
