package views.ComponentsModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonModelHover extends JButton {

    private float opacity = 0.5f;

    public ButtonModelHover(String pathIcon, String text, ActionListener actionListener, String event) {
        super(text);
        setActionCommand(event);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        addMouseListener(new EventButton());
        addActionListener(actionListener);
        setHorizontalTextPosition( SwingConstants.CENTER );
        setVerticalTextPosition( SwingConstants.BOTTOM );
        setForeground(Color.white);
        this.setIcon(new ImageIcon(getClass().getResource(pathIcon)));
        setFocusable(false);
        setAlignmentX(CENTER_ALIGNMENT);
        setContentAreaFilled(false);
        setBorder(null);
    }
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        super.paintComponent(g2);
    }

    public float getOpacity() {
        return opacity;
    }

    public void setOpacity(float opacity) {
        this.opacity = opacity;
        repaint();
    }


    public class EventButton extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent me) {
            efectHover(1f, 0.5f, 0.03f, 10, false);
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            efectHover(0.5f, 1f, 0.03f, 10, true);

        }

        @Override
        public void mousePressed(MouseEvent me) {
            efectHover(1f, 0.6f, 0.1f, 1, false);
        }

        private void efectHover(float index, float range, float cont, int sleep, boolean event) {
            new Thread(() -> {
                for (float i = index; (event) ? i <= range : i >= range; i = (event) ? i + cont : i - cont) {
                    setOpacity(i);
                    try {
                        Thread.sleep(sleep);
                    } catch (InterruptedException e) {
                    }

                }
            }).start();
        }
    }
}
