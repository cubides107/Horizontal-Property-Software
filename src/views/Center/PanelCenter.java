package views.Center;

import views.header.MainHeader;

import javax.swing.*;
import java.awt.*;

public class PanelCenter extends JPanel {

    private MainHeader mainHeader;

    public PanelCenter() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#1C2868"));
        initComponents();
    }

    private void initComponents() {

        mainHeader = new MainHeader();
        add(mainHeader, BorderLayout.NORTH);
    }
}
