package Presenter;

import Network.AdminApp;
import Network.PresenterImp;
import views.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Presenter implements PresenterImp, ActionListener {
    private MainFrame mainFrame;
    private AdminApp adminApp;

    public Presenter() {
        try {
            mainFrame = new MainFrame(this);
            adminApp = new AdminApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                adminApp.writeUTF("REGISTER_USER");
                adminApp.writeUTF(mainFrame.getDataUser()[0]);
                mainFrame.showLogin(false);
                break;
        }
    }

    @Override
    public void showAlert(boolean statusAlert) {
        if (statusAlert) {
            JOptionPane.showMessageDialog(null, "Ok");
        }else {
            JOptionPane.showMessageDialog(null,"Se encuentra un Usuario Con ese Nombre");
        }
    }
}
