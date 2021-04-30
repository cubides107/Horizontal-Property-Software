package views.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginAndRegisterPanel extends JPanel {

    public static final String LOGIN_PANEL = "loginPanel";
    public static final String REGISTER_PANEL = "registerPanel";
    private CardLayout cardLayout;

    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;

    public LoginAndRegisterPanel(ActionListener actionListener, ActionListener listenerPresenter) {
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        initComponents(actionListener, listenerPresenter);
        cardLayout.show(this,LOGIN_PANEL);
    }

    private void initComponents(ActionListener actionListener, ActionListener listenerPresenter) {
        loginPanel = new LoginPanel(actionListener, listenerPresenter);
        add(loginPanel , LOGIN_PANEL);

        registerPanel = new RegisterPanel(actionListener, listenerPresenter);
        add(registerPanel, REGISTER_PANEL);
    }

    public void showPanelLogin(){
        cardLayout.show(this,LOGIN_PANEL);
    }

    public void showPanelRegister(){
        cardLayout.show(this,REGISTER_PANEL);
    }

    public String[] getDataUser(){
        return registerPanel.getDataUser();
    }
}
