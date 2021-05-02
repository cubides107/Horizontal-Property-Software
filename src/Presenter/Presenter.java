package Presenter;

import Network.AdminApp;
import Network.PresenterImp;
import models.Node;
import models.TypeFiles;
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
        mainFrame.setNodeRoot(new Node(TypeFiles.HORIZONTAL_PROPERTY,nameProperty, "Super"));
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

                break;
            case ADD_BUILDING:
                adminApp.writeUTF(NEW_BUILDING);
                break;
            case ADD_HOUSE:
                adminApp.writeUTF(NEW_HOUSE);

                break;
            case ADD_APARTMENT:
                adminApp.writeUTF("NEW_APARTMENT");
                int idSelectNode = Integer.parseInt(mainFrame.getIdSelectNode());
                adminApp.writeInt(idSelectNode);
                break;
        }
    }

    @Override
    public void showAlert(boolean statusAlert) {
        if (statusAlert) {
            JOptionPane.showMessageDialog(null, "Ok");
        } else {
            JOptionPane.showMessageDialog(null, "Se encuentra un Usuario Con ese Nombre");
        }
    }

    @Override
    public void addNewBuilding(String idProperty) {
        mainFrame.addElementToRoot(new Node(TypeFiles.BUILDING, "Edificio",   idProperty));
    }

    @Override
    public void addNewHouse(String idProperty) {
        mainFrame.addElementToRoot(new Node(TypeFiles.HOUSE,"Casa" ,idProperty));
    }

    @Override
    public void addNewApartment(String idProperty) {
        mainFrame.addElementToNode(new Node(TypeFiles.APARTMENT,"Apartamento" , idProperty));
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            String selectNode = mainFrame.getSelectNameNode();
            if (selectNode.equals(TypeFiles.HORIZONTAL_PROPERTY.getType())) {
                mainFrame.showPopMenu(e.getComponent(), e.getX(), e.getY(), 0);
            } else if (selectNode.equals(TypeFiles.BUILDING.getType())) {
                mainFrame.showPopMenu(e.getComponent(), e.getX(), e.getY(), 1);
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
