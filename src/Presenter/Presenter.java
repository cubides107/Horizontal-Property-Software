package Presenter;

import Network.AdminApp;
import Network.PresenterImp;
import models.Node;
import models.TypeFiles;
import views.Center.PropertiesPanel;
import views.Center.UsersPanel;
import views.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

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
        mainFrame.setNodeRoot(new Node(TypeFiles.HORIZONTAL_PROPERTY, nameProperty, "Super"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (Events.valueOf(e.getActionCommand())) {
            case ADD_USER:
                mainFrame.showLogin(true);
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
                break;
            case SHOW_PROPERTIES_PANEL:
                mainFrame.showPropertiesPanel();
                break;
            case ADD_HOUSE_USER:
                adminApp.writeUTF("ADD_HOUSE_USER");
                int idSelectNodeUsers = Integer.parseInt(mainFrame.getIdSelectNodeUsers());
                adminApp.writeInt(idSelectNodeUsers);
                adminApp.writeUTF(NEW_HOUSE);
                break;
            case ADD_APARTMENT_USER:
                mainFrame.showPropertiesPanel();
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
                mainFrame.setActionCommandAddButton();
                break;
            case SELECT_PROPERTY:
                mainFrame.showUserPanel();
                idSelectNode = Integer.parseInt(mainFrame.getIdSelectNodeProperties());
                String selectNode = mainFrame.getSelectNameNode();
                String idSelectNodeUsers1 = mainFrame.getIdSelectNodeUsers();
                System.out.println("NodeProperties: " + idSelectNode + " Nodeusers: " + idSelectNodeUsers1);
                if (selectNode.equals(TypeFiles.APARTMENT.getType()) || selectNode.equals(TypeFiles.HOUSE.getType())) {
                    adminApp.writeUTF("SET_PROPERTY_TO_USER");
                    adminApp.writeInt(Integer.parseInt(idSelectNodeUsers1));
                    adminApp.writeInt(idSelectNode);
                    if (selectNode.equals(TypeFiles.APARTMENT.getType())) {
                        mainFrame.addElementToNodeUsers(new Node(TypeFiles.APARTMENT, "Apartamento", String.valueOf(idSelectNode)));
                    } else if (selectNode.equals(TypeFiles.HOUSE.getType())) {
                        mainFrame.addElementToNodeUsers(new Node(TypeFiles.HOUSE, "Casa", String.valueOf(idSelectNode)));
                    }
                } else {

                }
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
                }
                break;
        }
    }

    @Override
    public void showAlert(boolean statusAlert, String nameUser, int idUser) {
        if (statusAlert) {
            JOptionPane.showMessageDialog(null, "Ok");
            mainFrame.addElementToRootUser(new Node(TypeFiles.USER, nameUser, String.valueOf(idUser)));
        } else {
            JOptionPane.showMessageDialog(null, "Se encuentra un Usuario Con ese Nombre");
        }
    }

    @Override
    public void addNewBuilding(String idProperty) {
        mainFrame.addElementToRoot(new Node(TypeFiles.BUILDING, "Edificio", idProperty));
    }

    @Override
    public void addNewHouse(String idProperty) {
        mainFrame.addElementToRoot(new Node(TypeFiles.HOUSE, "Casa", idProperty));
    }

    @Override
    public void addNewApartment(String idProperty) {
        mainFrame.addElementToNode(new Node(TypeFiles.APARTMENT, "Apartamento", idProperty));
    }

    @Override
    public void addUser(String emailUser) {
        mainFrame.addElementToRootUser(new Node(TypeFiles.USER, "User", emailUser));
    }

    @Override
    public void addHouseToUser(int idProperty) {
        mainFrame.addElementToNodeUsers(new Node(TypeFiles.HOUSE, "Casa", String.valueOf(idProperty)));
    }

    @Override
    public void addApartmentToUser(int idProperty) {
        mainFrame.addElementToNodeUsers(new Node(TypeFiles.APARTMENT, "Apartamento", String.valueOf(idProperty)));
    }

    @Override
    public void addNewPool(String idProperty) {
        mainFrame.addElementToRoot(new Node(TypeFiles.POOL, "Piscina", idProperty));
    }

    @Override
    public void addNewField(String idProperty) {
        mainFrame.addElementToRoot(new Node(TypeFiles.FIELD, "Cancha", idProperty));
    }

    @Override
    public void addNewCommonRoom(String idProperty) {
        mainFrame.addElementToRoot(new Node(TypeFiles.COMMON_ROOM, "Salon Comunal", idProperty));
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
