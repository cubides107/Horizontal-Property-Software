package views;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TextFieldModel extends JTextField{
	

	private static final long serialVersionUID = 1L;

	public TextFieldModel(String name) {
		setOpaque(false);
		setBackground(Color.WHITE);
		setMaximumSize(new Dimension(200,400));
		setForeground(Color.black);
		Border empty = new EmptyBorder(5,10,5,5);
		setBorder(empty);
		new TextPrompt(name, this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.white);
		g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1,20,20);
		super.paintComponent(g);
	}
}
