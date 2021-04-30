package views.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JPMainPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    private ImagesPanel imagesPanel;
    private LoginAndRegisterPanel loginAndRegisterPanel;

    public JPMainPanel(ActionListener actionListener) {

        setLayout(new GridLayout(0,2));
        setBackground(new Color(33, 44, 63));
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {

        imagesPanel = new ImagesPanel(actionListener);
        add(imagesPanel);

        loginAndRegisterPanel = new LoginAndRegisterPanel(this, actionListener);
        add(loginAndRegisterPanel);

    }

    private ImageIcon createIcon(String path, int weight, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(weight, height, Image.SCALE_DEFAULT));
        return icono;
    }

    public String[] getDataUser(){
        return  loginAndRegisterPanel.getDataUser();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (EventsView.valueOf(e.getActionCommand())){
            case REGISTER_PANEL:
                loginAndRegisterPanel.showPanelRegister();
                break;
            case LOGIN_PANEL:
                loginAndRegisterPanel.showPanelLogin();
                break;
        }
    }
}
