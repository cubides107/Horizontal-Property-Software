package Presenter;

import Network.AdminApp;
import Network.PresenterImp;
import models.NodeTreeViews;
import models.TypeFiles;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import persistence.Persistence;
import views.Center.PropertiesPanel;
import views.Center.UsersPanel;
import views.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.time.LocalDate;

public class Presenter implements PresenterImp, ActionListener, MouseListener {
    public static final String NEW_HOUSE = "NEW_HOUSE";
    public static final String REGISTER_USER = "REGISTER_USER";
    public static final String NEW_BUILDING = "NEW_BUILDING";
    public static final String SHOW_PROPERTIES = "SHOW_PROPERTIES";
    private MainFrame mainFrame;
    private AdminApp adminApp;


    public Presenter() {
        try {
            mainFrame = new MainFrame(this, this);
            adminApp = new AdminApp(this);
            initConfiguratios();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initConfiguratios() {
        String nameProperty = JOptionPane.showInputDialog(null, "Ingrese El Nombre de la Propiedad Horizontal");
        adminApp.writeUTF("CREATE_HORIZONTAL_PROPERTY");
        adminApp.writeUTF(nameProperty);
        mainFrame.setNodeRoot(new NodeTreeViews(TypeFiles.HORIZONTAL_PROPERTY, nameProperty, "Super"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Events.valueOf(e.getActionCommand())) {
            case ADD_USER:
                mainFrame.showDialogReport(true);
                break;
            case ACCEPT_REPORT:
                LocalDate[] date = mainFrame.getDate();
                adminApp.writeUTF("REPORT3");
                adminApp.writeUTF(date[0].toString() + "#" + date[1].toString());
                mainFrame.showDialogReport(false);
                break;
            case EXIT:
                mainFrame.showLogin(false);
                break;
            case REGISTER_USER:
                System.out.println(mainFrame.getDataUser()[0] + "Password" + mainFrame.getDataUser()[1]);
                adminApp.writeUTF(REGISTER_USER);
                adminApp.writeUTF(mainFrame.getDataUser()[0]);
                mainFrame.showLogin(false);
                break;
            case PROPERTIES:
                adminApp.writeUTF(SHOW_PROPERTIES);
                break;
            case ADD_BUILDING:
                adminApp.writeUTF(NEW_BUILDING);
                break;
            case ADD_HOUSE:
                adminApp.writeUTF(NEW_HOUSE);
                break;
            case ADD_APARTMENT:
                adminApp.writeUTF("NEW_APARTMENT");
                int idSelectNode = Integer.parseInt(mainFrame.getIdSelectNodeProperties());
                adminApp.writeInt(idSelectNode);
                break;
            case ADD_POOL:
                adminApp.writeUTF("NEW_POOL");
                break;
            case FIELD:
                adminApp.writeUTF("NEW_FIELD");
                break;
            case ADD_COMMON_ROOM:
                adminApp.writeUTF("NEW_ADD_COMMON_ROOM");
                break;
            case ADD_USERS_POP_MENU:
                String emailUser = JOptionPane.showInputDialog(null, "Correo usuario");
                adminApp.writeUTF(REGISTER_USER);
                adminApp.writeUTF(emailUser);
                break;
            case SHOW_USERS_PANEL:
                mainFrame.showUserPanel();
                adminApp.writeUTF("SHOW_USERS_PANEL");
                break;
            case SHOW_PROPERTIES_PANEL:
                mainFrame.showPropertiesPanel();
                adminApp.writeUTF(SHOW_PROPERTIES);
                break;
            case REPORTS:
                adminApp.writeUTF("REPORT2");
                mainFrame.showTableReports();

                break;
            case ADD_HOUSE_USER:
                adminApp.writeUTF(NEW_HOUSE);
                adminApp.writeUTF("ADD_HOUSE_USER");
                int idSelectNodeUsers = Integer.parseInt(mainFrame.getIdSelectNodeUsers());
                adminApp.writeInt(idSelectNodeUsers);
                break;
            case ADD_APARTMENT_USER:
                mainFrame.showPropertiesPanel();
                JOptionPane.showMessageDialog(null,"Seleccione un Edificio");
                mainFrame.showButtonAdd(true);
                break;
            case ADD_APARTMENT_TREE_PROPERTIES:
                mainFrame.showUserPanel();
                mainFrame.showButtonAdd(false);

                adminApp.writeUTF("NEW_APARTMENT");
                idSelectNode = Integer.parseInt(mainFrame.getIdSelectNodeProperties());
                adminApp.writeInt(idSelectNode);

                adminApp.writeUTF("ADD_APARTMENT_USER");
                int idSelectNodeUsersApartment = Integer.parseInt(mainFrame.getIdSelectNodeUsers());
                adminApp.writeInt(idSelectNodeUsersApartment);
                break;
            case SELECT_PROPERTY_TO_USER:
                mainFrame.showPropertiesPanel();
                JOptionPane.showMessageDialog(null,"Seleccione Una Propiedad");
                mainFrame.setActionCommandAddButton();
                break;
            case SELECT_PROPERTY:
                mainFrame.showUserPanel();
                idSelectNode = Integer.parseInt(mainFrame.getIdSelectNodeProperties());
                String selectNode = mainFrame.getSelectNameNode();
                String idSelectNodeUsers1 = mainFrame.getIdSelectNodeUsers();
                if (selectNode.equals(TypeFiles.APARTMENT.getType()) || selectNode.equals(TypeFiles.HOUSE.getType())) {
                    adminApp.writeUTF("SET_PROPERTY_TO_USER");
                    adminApp.writeInt(Integer.parseInt(idSelectNodeUsers1));
                    adminApp.writeInt(idSelectNode);
                    adminApp.writeUTF(selectNode);
                    if (selectNode.equals(TypeFiles.APARTMENT.getType())) {
                        mainFrame.addElementToNodeUsers(new NodeTreeViews(TypeFiles.APARTMENT, "Apartamento", String.valueOf(idSelectNode)));
                    } else if (selectNode.equals(TypeFiles.HOUSE.getType())) {
                        mainFrame.addElementToNodeUsers(new NodeTreeViews(TypeFiles.HOUSE, "Casa", String.valueOf(idSelectNode)));
                    }
                }
                mainFrame.setResetCommandButtonAdd();
                mainFrame.showButtonAdd(false);
                break;
            case DELETE_PROPERTY:
                boolean showingUsersPanel = mainFrame.isShowingUsersPanel();
                boolean showingPropertiesPanel = mainFrame.isShowingPropertiesPanel();
                if (showingUsersPanel) {
                    idSelectNodeUsers = Integer.parseInt(mainFrame.getIdSelectNodeUsers());
                    adminApp.writeUTF("DELETE_USER");
                    adminApp.writeInt(idSelectNodeUsers);
                    mainFrame.removeElementToTreeUsers();

                } else if (showingPropertiesPanel) {
                    idSelectNode = Integer.parseInt(mainFrame.getIdSelectNodeProperties());
                    adminApp.writeUTF("DELETE_PROPERTY");
                    adminApp.writeInt(idSelectNode);
                    mainFrame.removeElementToTreeProperties();
                    mainFrame.removeElementToTreeUsersById(idSelectNode);
                }
                break;
            case DELETE_PROPERTY_TO_USER:
                showingUsersPanel = mainFrame.isShowingUsersPanel();
                if (showingUsersPanel) {
                    idSelectNodeUsers = Integer.parseInt(mainFrame.getIdSelectNodeUsers());
                    adminApp.writeUTF("DELETE_PROPERTY_TO_USER");
                    adminApp.writeInt(idSelectNodeUsers);
                    mainFrame.removeElementToTreeUsers();
                }
                break;
            case EDIT_USER:
                idSelectNodeUsers = Integer.parseInt(mainFrame.getIdSelectNodeUsers());
                String email = JOptionPane.showInputDialog(null, "Ingrese el nuevo Correo");
                adminApp.writeUTF("EDIT_USER");
                adminApp.writeInt(idSelectNodeUsers);
                adminApp.writeUTF(email);

                break;
        }
    }

    @Override
    public void showAlert(boolean statusAlert, String nameUser, int idUser) {
        if (statusAlert) {
            JOptionPane.showMessageDialog(null, "Ok");
            System.out.println(nameUser + idUser);
            mainFrame.addElementToRootUser(new NodeTreeViews(TypeFiles.USER, nameUser, String.valueOf(idUser)));
        } else {
            JOptionPane.showMessageDialog(null, "Se encuentra un Usuario Con ese Nombre");
        }
    }

    @Override
    public void addNewBuilding(String idProperty) {
        mainFrame.addElementToRoot(new NodeTreeViews(TypeFiles.BUILDING, "Edificio", idProperty));
    }

    @Override
    public void addNewHouse(String idProperty) {
        mainFrame.addElementToRoot(new NodeTreeViews(TypeFiles.HOUSE, "Casa", idProperty));
    }

    @Override
    public void addNewApartment(String idProperty) {
        mainFrame.addElementToNode(new NodeTreeViews(TypeFiles.APARTMENT, "Apartamento", idProperty));
    }

    @Override
    public void addUser(String emailUser) {
        mainFrame.addElementToRootUser(new NodeTreeViews(TypeFiles.USER, "User", emailUser));
    }

    @Override
    public void addHouseToUser(int idProperty) {
        mainFrame.addElementToNodeUsers(new NodeTreeViews(TypeFiles.HOUSE, "Casa", String.valueOf(idProperty)));
    }

    @Override
    public void addApartmentToUser(int idProperty) {
        mainFrame.addElementToNodeUsers(new NodeTreeViews(TypeFiles.APARTMENT, "Apartamento", String.valueOf(idProperty)));
    }

    @Override
    public void addNewPool(String idProperty) {
        mainFrame.addElementToRoot(new NodeTreeViews(TypeFiles.POOL, "Piscina", idProperty));
    }

    @Override
    public void addNewField(String idProperty) {
        mainFrame.addElementToRoot(new NodeTreeViews(TypeFiles.FIELD, "Cancha", idProperty));
    }

    @Override
    public void addNewCommonRoom(String idProperty) {
        mainFrame.addElementToRoot(new NodeTreeViews(TypeFiles.COMMON_ROOM, "Salon Comunal", idProperty));
    }

    @Override
    public void loadPropertiesTree() {
        Document document = Persistence.convertXMLFileToXMLDocument("data/Properties.xml");
        Node root = document.getDocumentElement();
        mainFrame.loadDataProperties(root);
    }

    @Override
    public void loadDataUsers() {
        Document document = Persistence.convertXMLFileToXMLDocument("data/Users.xml");
        Node root = document.getDocumentElement();
        mainFrame.loadDataUsers(root);
    }

    @Override
    public void showReportUsers() {
        mainFrame.showReportUsers();
    }

    @Override
    public void repaintNodesPropertiesTre(String idNodes) {
        mainFrame.repaintNodes(idNodes);
        mainFrame.showPropertiesPanel();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {


            boolean showingUsersPanel = mainFrame.isShowingUsersPanel();
            boolean showingPropertiesPanel = mainFrame.isShowingPropertiesPanel();
            if (showingUsersPanel) {
                UsersPanel component = (UsersPanel) e.getComponent().getParent();
                String selectNode = component.getSelectTypeNode();
                if (selectNode.equals(TypeFiles.HORIZONTAL_PROPERTY_USER.getType())) {
                    component.showPopMenu(e.getComponent(), e.getX(), e.getY(), 0);
                } else if (selectNode.equals(TypeFiles.USER.getType())) {
                    component.showPopMenu(e.getComponent(), e.getX(), e.getY(), 1);
                } else if (selectNode.equals(TypeFiles.HOUSE.getType()) || selectNode.equals(TypeFiles.APARTMENT.getType())) {
                    component.showPopMenuDeletePropertyToUser(e.getComponent(), e.getX(), e.getY());
                }

            } else if (showingPropertiesPanel) {
                String selectNode = mainFrame.getSelectNameNode();
                if (selectNode.equals(TypeFiles.HORIZONTAL_PROPERTY.getType())) {
                    mainFrame.showPopMenu(e.getComponent(), e.getX(), e.getY(), 0, 1);
                } else if (selectNode.equals(TypeFiles.BUILDING.getType())) {
                    mainFrame.showPopMenu(e.getComponent(), e.getX(), e.getY(), 1, 1);
                } else if (selectNode.equals(TypeFiles.FIELD.getType()) || selectNode.equals(TypeFiles.HOUSE.getType()) || selectNode.equals(TypeFiles.APARTMENT.getType()) ||
                        selectNode.equals(TypeFiles.COMMON_ROOM.getType()) || selectNode.equals(TypeFiles.POOL.getType())) {
                    PropertiesPanel component = (PropertiesPanel) e.getComponent().getParent();
                    component.showDeletePopMenu(e.getComponent(), e.getX(), e.getY());
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
