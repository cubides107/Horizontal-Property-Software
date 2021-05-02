package views;

import models.Node;
import views.Center.PanelCenter;
import views.dashboard.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class MainPanel  extends JPanel {

    private Dashboard dashboard;
    private PanelCenter panelCenter;


    public MainPanel(ActionListener actionListener, MouseListener mouseListener) {
        setLayout(new BorderLayout());
        initComponents(actionListener,mouseListener);
    }

    private void initComponents(ActionListener actionListener, MouseListener mouseListener) {
        panelCenter = new PanelCenter(mouseListener,actionListener);
        add(panelCenter, BorderLayout.CENTER);

        dashboard = new Dashboard(actionListener);
        add(dashboard,BorderLayout.WEST);

    }

    public void setNodeRoot(Node nodeRoot){
        panelCenter.setNodeRoot(nodeRoot);
    }

    public void addElementToRoot(Node node){
       panelCenter.addElementToRoot(node);
    }

    public void showPopMenu(Component component,int x, int y,int idPopMenu) {
        panelCenter.showPopMenu(component,x,y,idPopMenu);
    }

    public String getSelectNameNode() {
        return panelCenter.getSelectTypeNode();
    }

    public void addElementToNode(Node node) {
        panelCenter.addElementToNode(node);
    }

    public String getIdSelectNode() {
    return panelCenter.getIdSelectNode();
    }
}
