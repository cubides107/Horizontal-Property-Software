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
import javax.swing.tree.MutableTreeNode;
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

    public void loadData(Node root) {
        modelTree.setRoot(builtTreeNode(root));
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
//                    else if (tempNode.getNodeName().equals("Date")) {
//                        String nodeValue = tempNode.getTextContent();
//                        NodeTreeViews userObject = (NodeTreeViews) dmtNode.getUserObject();
//                        userObject.setID(nodeValue);
//                    }


                    if (!(tempNode.getNodeName().equals("ID") || tempNode.getNodeName().equals("Date") || tempNode.getNodeName().equals("Value") )) {
                        dmtNode.add(builtTreeNode(tempNode));
                    }
                }
            }
        }
        return dmtNode;
    }

    private NodeTreeViews caseNodeTree(Node root, NodeTreeViews node) {
        if (root.getParentNode().getNodeName().equals("#document")) {
            return node = new NodeTreeViews(TypeFiles.HORIZONTAL_PROPERTY_USER, root.getNodeName(), "0");
        }
        switch (root.getNodeName()) {
            case "House":
                node = new NodeTreeViews(TypeFiles.HOUSE, root.getNodeName(), "0");
                break;
            case "Apartment":
                node = new NodeTreeViews(TypeFiles.APARTMENT, root.getNodeName(), "0");
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
                node = new NodeTreeViews(TypeFiles.USER, root.getNodeName(), "0");
                break;
        }
        return node;
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

    public void addElementToRoot(NodeTreeViews node) {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) modelTree.getRoot();
        root.add(new DefaultMutableTreeNode(node));
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
            popMenuUsers.show(component, x, y);
        }
    }

    public void setNodeRoot(NodeTreeViews nodeRoot) {
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
        NodeTreeViews nodeAux = (NodeTreeViews) actual.getUserObject();
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
