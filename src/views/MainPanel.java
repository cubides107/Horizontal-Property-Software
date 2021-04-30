package views;

import views.Center.PanelCenter;
import views.dashboard.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainPanel  extends JPanel {

    private Dashboard dashboard;
    private PanelCenter panelCenter;


    public MainPanel(ActionListener actionListener) {
        setLayout(new BorderLayout());
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {
        panelCenter = new PanelCenter();
        add(panelCenter, BorderLayout.CENTER);

        dashboard = new Dashboard(actionListener);
        add(dashboard,BorderLayout.WEST);

    }
}
