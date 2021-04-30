package views.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainLogin extends JDialog {


    private static final long serialVersionUID = 1L;
    private JPMainPanel jpMainPanel;

    public MainLogin(ActionListener actionListener) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(new Dimension(700, 470));
        setModal(true);
        setUndecorated(true);
        setLocationRelativeTo(null);
        iniComponents(actionListener);
    }

    private void iniComponents(ActionListener actionListener) {
        jpMainPanel = new JPMainPanel(actionListener);
        add(jpMainPanel);
    }

    public String[] getDataUser(){
        return jpMainPanel.getDataUser();
    }

}
