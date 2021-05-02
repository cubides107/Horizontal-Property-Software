package views;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenuItemModel extends JMenuItem {

    public MenuItemModel(String text, ActionListener actionListener, String command) {
        addActionListener(actionListener);
        setActionCommand(command);
        setText(text);
    }
}
