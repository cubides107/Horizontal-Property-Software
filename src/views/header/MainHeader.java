package views.header;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MainHeader extends JPanel {

    public MainHeader() {
        setBackground(Color.decode("#1C2868"));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(new EmptyBorder(20,15,10,15));
        initComponents();
    }

    private void initComponents() {
        JLabel clock = new JLabel();
        clock.setForeground(Color.white);
        add(clock);
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("EEEE d. y  hh:mm: a");
        Timer timer = new Timer(1000, e -> {
            clock.setText(formateador.format(LocalDateTime.now()));
        });
        timer.start();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g.setColor(Color.WHITE);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawImage(new ImageIcon(getClass().getResource("/super_user.png")).getImage(),830,10,32,32,this);
        g2.drawString("Admin",780,30);
    }

}
