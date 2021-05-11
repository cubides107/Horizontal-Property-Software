package views;

import models.INode;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class TreeCellRenderer extends DefaultTreeCellRenderer {

    public static final Color BLUE_COLOR = Color.decode("#1C2868");

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        setBorder(new EmptyBorder(2,0,2,0));
        setBackgroundNonSelectionColor(BLUE_COLOR);
        setBackgroundSelectionColor(BLUE_COLOR);
        setFocusable(false);
        setBorderSelectionColor(BLUE_COLOR);
        setTextSelectionColor(Color.red);
        setTextNonSelectionColor(Color.WHITE);

        Object node = ((DefaultMutableTreeNode) value).getUserObject();
        if (node instanceof INode) {
            setIcon(((INode) node).getTypeFile().getIcon());
           if(((INode) node).getIsPainted()){
               setForeground(Color.GREEN);
           }
        } else {
            setIcon(null);
        }
        return this;
    }
}
