package views.login;

import Presenter.Events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ImagesPanel extends JPanel {

    public ImagesPanel(ActionListener actionListener) {
        setLayout(null);
        setBackground(Color.BLACK);
        RoundedJButton exitButton = new RoundedJButton("Exit", Events.EXIT.name(), actionListener,Color.red,Color.white);
        exitButton.setBounds(20,430,80,25);
        add(exitButton);
    }

    @Override
    public void paint(Graphics g) {
        setOpaque(false);
        g.drawImage(new ImageIcon(getClass().getResource("/loginImages/img1.jpg")).getImage(),0,0,getWidth(),getHeight(),this);
        super.paint(g);
    }
}
