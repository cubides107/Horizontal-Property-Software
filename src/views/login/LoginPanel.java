package views.login;

import Presenter.Events;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private JTextFieldModel userName;
    private PasswordFieldModel passwordField;

    public LoginPanel( ActionListener actionListener,ActionListener listenerPresenter) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(38,30,38,30));
        setBackground(Color.WHITE);

        add(Box.createRigidArea(new Dimension(0, 70)));

        JLabel title = new JLabel("Inicio de Sesión");
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setForeground(Color.decode("#0A7EF5"));
        title.setFont(new Font("Arial", Font.BOLD,19));
        add(title);

        add(Box.createRigidArea(new Dimension(0, 50)));
        userName = new JTextFieldModel("UserName");
        userName.setIcon(createIcon("/loginImages/user.png", 16, 16));
        add(userName);

        add(Box.createRigidArea(new Dimension(0, 20)));
        passwordField = new PasswordFieldModel("Password", Color.white);
        passwordField.setIcon(createIcon("/loginImages/key.png", 16, 16));
        add(passwordField);

        add(Box.createRigidArea(new Dimension(0, 20)));
        RoundedJButton loginUser = new RoundedJButton("INGRESAR", Events.SAVE_USER.name(), listenerPresenter, Color.decode("#0A7EF5"),Color.WHITE);
        loginUser.setAlignmentX(CENTER_ALIGNMENT);
        add(loginUser);

        add(Box.createRigidArea(new Dimension(0, 3)));
        RoundedJButton changePanel = new RoundedJButton("Registrar Usuario", EventsView.REGISTER_PANEL.name(), actionListener,Color.WHITE,Color.decode("#0A7EF5"));
        changePanel.setAlignmentX(CENTER_ALIGNMENT);
        add(changePanel);
        add(Box.createRigidArea(new Dimension(0, 70)));

    }

    private ImageIcon createIcon(String path, int weight, int height) {
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(weight, height, Image.SCALE_DEFAULT));
        return icono;
    }
}
