package views;

import javax.swing.*;

public class AddElement extends JDialog {


    public static final String NAME_HOUSE = "Nombre de Familia";
    private JPanel panel;
    private TextFieldModel textFieldModel;



    public AddElement() {
        setSize(500,500);
        setLocationRelativeTo(null);
        initComponents();
        panel = new JPanel();
    }

    private void initComponents() {

        add(textFieldModel);

        add(panel);
    }

    public void showDialog(boolean option){
            setVisible(option);
    }
}
