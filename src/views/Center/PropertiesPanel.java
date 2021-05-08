package views.Center;

import Presenter.Events;
import models.NodeTreeViews;
import models.TypeFiles;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
    JPopupMenu deletePropertyOnly;
    private JButton add;

    public PropertiesPanel(MouseListener mouseListener, ActionListener actionListener) {
        popMenuRoot = new JPopupMenu();
        popMenuBuilding = new JPopupMenu();
        deletePropertyOnly = new JPopupMenu();
        setLayout(new BorderLayout());
        setBackground(Color.decode("#1C2868"));
        nodeRoot = new DefaultMutableTreeNode();
        model = new DefaultTreeModel(nodeRoot);
        initComponents(mouseListener, actionListener);
    }

    public void setNodeRoot(NodeTreeViews nodeRoot) {
        this.nodeRoot.setUserObject(nodeRoot);
        tree.updateUI();
    }

    private void initComponents(MouseListener mouseListener, ActionListener actionListener) {

        JMenu menu = new JMenu("Agregar");
        MenuItemModel removeElement = new MenuItemModel("Eliminar", actionListener, Events.DELETE_PROPERTY.name());
        menu.add(new MenuItemModel("Edificio", actionListener, Events.ADD_BUILDING.name()));
        menu.add(new MenuItemModel("Casa", actionListener, Events.ADD_HOUSE.name()));
        menu.add(new MenuItemModel("Salon comunal ", actionListener, Events.ADD_COMMON_ROOM.name()));
        menu.add(new MenuItemModel("Piscina ", actionListener, Events.ADD_POOL.name()));
        menu.add(new MenuItemModel("Cancha", actionListener, Events.FIELD.name()));
        popMenuRoot.add(menu);
        popMenuRoot.add(new MenuItemModel("Eliminar", actionListener, Events.DELETE_PROPERTY.name()));


        JMenu menuBuilding = new JMenu("Agregar");
        menuBuilding.add(new MenuItemModel("Apartamento", actionListener, Events.ADD_APARTMENT.name()));
        popMenuBuilding.add(menuBuilding);
        popMenuBuilding.add(new MenuItemModel("Eliminar", actionListener, Events.DELETE_PROPERTY.name()));


        deletePropertyOnly.add(removeElement);

        tree = new JTree(model);
        tree.addMouseListener(mouseListener);
        tree.setCellRenderer(new TreeCellRenderer());
        tree.setShowsRootHandles(true);
        tree.setOpaque(false);
        add(tree, BorderLayout.CENTER);

        add = new JButton("Agregar");
        add.addActionListener(actionListener);
        add.setActionCommand(Events.ADD_APARTMENT_TREE_PROPERTIES.name());
        add.setVisible(false);
        add(add, BorderLayout.SOUTH);

    }

    public void showButtonAdd(boolean b) {
        add.setVisible(b);
    }

    public void setActionCommandAddButton() {
        add.setVisible(true);
        add.setActionCommand(Events.SELECT_PROPERTY.name());
    }

    public void resetCommandButtonAdd(){
        add.setActionCommand(Events.ADD_APARTMENT_TREE_PROPERTIES.name());
    }

    public void removeElementToTree() {
        model.removeNodeFromParent(getSelectNode());
    }


    public String getSelectTypeNode() {
        TreeSelectionModel treeSelectionModel = tree.getSelectionModel();
        if (treeSelectionModel.getSelectionPath() != null) {
            DefaultMutableTreeNode lastPathComponent = (DefaultMutableTreeNode) treeSelectionModel.getSelectionPath().getLastPathComponent();
            NodeTreeViews userObject = (NodeTreeViews) lastPathComponent.getUserObject();
            return userObject.getTypeFile().getType();
        } else {
            return " ";
        }
    }

    public String getIdSelectNode() {
        TreeSelectionModel treeSelectionModel = tree.getSelectionModel();
        if (treeSelectionModel.getSelectionPath() != null) {
            DefaultMutableTreeNode lastPathComponent = (DefaultMutableTreeNode) treeSelectionModel.getSelectionPath().getLastPathComponent();
            NodeTreeViews userObject = (NodeTreeViews) lastPathComponent.getUserObject();
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

    public void showDeletePopMenu(Component component, int x, int y) {
        deletePropertyOnly.show(component, x, y);
    }

    public void addElementToRoot(NodeTreeViews node) {
        DefaultMutableTreeNode nodeRoot = (DefaultMutableTreeNode) model.getRoot();
        nodeRoot.add(new DefaultMutableTreeNode(node));
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tree.updateUI();
    }

    public void addElementToNode(NodeTreeViews node) {
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

    public void loadDataProperties(Node root) {
        model.setRoot(builtTreeNode(root));
    }

    private DefaultMutableTreeNode builtTreeNode(Node root) {
        NodeTreeViews node = null;
        node = caseNodeTree(root, node);
        DefaultMutableTreeNode dmtNode = new DefaultMutableTreeNode(node);
        NodeList nodeList = root.getChildNodes();
        for (int count = 0; count < nodeList.getLength(); count++) {
            Node tempNode = nodeList.item(count);
            // make sure it's element node.
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                if (tempNode.hasChildNodes()) {
//                    String nodeValue = tempNode.getFirstChild().getNodeValue().trim();
//                    node.setID(nodeValue);
                    if (tempNode.getNodeName().equals("ID")) {
                        String nodeValue = tempNode.getTextContent();
                        NodeTreeViews userObject = (NodeTreeViews) dmtNode.getUserObject();
                        userObject.setID(nodeValue);
                    }
                    if(tempNode.getNodeName().equals("Date")){
                        DefaultMutableTreeNode date = new DefaultMutableTreeNode("Fecha: "  + tempNode.getTextContent());
                        dmtNode.add(date);
                    }
                    if(tempNode.getNodeName().equals("Value")){
                        DefaultMutableTreeNode date = new DefaultMutableTreeNode("valor: "  + tempNode.getTextContent());
                        dmtNode.add(date);
                    }
                    if (!(tempNode.getNodeName().equals("ID") || tempNode.getNodeName().equals("Date") || tempNode.getNodeName().equals("Value") )) {
                        dmtNode.add(builtTreeNode(tempNode));
                    }
//                    else {
//                        nodeValue = tempNode.getTextContent();
//                        node.setID(nodeValue);
//                    }
                }
            }
        }
        return dmtNode;
    }

    private NodeTreeViews caseNodeTree(Node root, NodeTreeViews node) {
        switch (root.getNodeName()) {
            case "House":
                node = new NodeTreeViews(TypeFiles.HOUSE, root.getNodeName(), "0");
                break;
            case "Apartment":
                node = new NodeTreeViews(TypeFiles.APARTMENT, root.getNodeName(), "0");
                break;
            case "Building":
                node = new NodeTreeViews(TypeFiles.BUILDING, root.getNodeName(), "0");
                break;
            case "CommonRoom":
                node = new NodeTreeViews(TypeFiles.COMMON_ROOM, root.getNodeName(), "0");
                break;
            case "Pool":
                node = new NodeTreeViews(TypeFiles.POOL, root.getNodeName(), "0");
                break;
            case "Field":
                node = new NodeTreeViews(TypeFiles.FIELD, root.getNodeName(), "0");
                break;
            case "ElectricityService":
                node = new NodeTreeViews(TypeFiles.SERVICE_ELECTRICITY, root.getNodeName(), "0");
                break;
            case "WrapperService":
                node = new NodeTreeViews(TypeFiles.BILL_SERVICE, root.getNodeName(), "0");
                break;
            case "GasService":
                node = new NodeTreeViews(TypeFiles.SERVICE_GAS, root.getNodeName(), "0");
                break;
            case "WaterService":
                node = new NodeTreeViews(TypeFiles.SERVICE_WATER, root.getNodeName(), "0");
                break;
            case "InternetService":
                node = new NodeTreeViews(TypeFiles.SERVICE_INTERNET, root.getNodeName(), "0");
                break;
            default:
                node = new NodeTreeViews(TypeFiles.HORIZONTAL_PROPERTY, root.getNodeName(), "0");
                break;
        }
        return node;
    }
}
