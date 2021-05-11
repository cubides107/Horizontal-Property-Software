package views;

import Presenter.Events;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;
import views.login.RoundedJButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class DialogReport extends JDialog {
    private RoundedJButton acceptButton;
    private JMonthChooser monthChooserStart;
    private JYearChooser yearChooserStart;

    private JMonthChooser monthChooserEnd;
    private JYearChooser yearChooserEnd;

    private JPanel container;

    public DialogReport(ActionListener actionListener) {
        container = new JPanel();
        setSize(300,300);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/IconApp1.png")).getImage());
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(new EmptyBorder(38, 30, 38, 30));
        initComponents(actionListener);
    }

    private void initComponents(ActionListener actionListener) {

        JLabel title = new JLabel("Rango de Fechas");
        title.setFont(new Font("Arial", Font.BOLD,24));
        title.setAlignmentX(CENTER_ALIGNMENT);
        container.add(title);
        container.add(Box.createRigidArea(new Dimension(20,10)));

        JPanel containerDateStart = new JPanel();
        containerDateStart.setBorder(BorderFactory.createTitledBorder("Fecha Inicial"));
        containerDateStart.setLayout(new BoxLayout(containerDateStart,BoxLayout.X_AXIS));
        monthChooserStart = new JMonthChooser();
        yearChooserStart = new JYearChooser();

        containerDateStart.add(monthChooserStart);
        containerDateStart.add(yearChooserStart);
        container.add(Box.createRigidArea(new Dimension(20,10)));
        container.add(containerDateStart);


        JPanel containerDateEnd = new JPanel();
        containerDateEnd.setBorder(BorderFactory.createTitledBorder("Fecha Final"));
        containerDateEnd.setLayout(new BoxLayout(containerDateEnd,BoxLayout.X_AXIS));
        monthChooserEnd = new JMonthChooser();
        yearChooserEnd = new JYearChooser();
        containerDateEnd.add(monthChooserEnd);
        containerDateEnd.add(yearChooserEnd);

        container.add(Box.createRigidArea(new Dimension(20,10)));
        container.add(containerDateEnd);

        acceptButton = new RoundedJButton("Aceptar", Events.ACCEPT_REPORT.name(), actionListener, Color.decode("#0A7EF5"), Color.WHITE);
        acceptButton.setAlignmentX(CENTER_ALIGNMENT);
        container.add(Box.createRigidArea(new Dimension(20,10)));
        container.add(acceptButton);
        add(container);
    }

    public LocalDate[] getDate(){
        LocalDate[] localDateArray = new LocalDate[2];
        localDateArray[0] = LocalDate.of(yearChooserStart.getYear(), monthChooserStart.getMonth()+1,1);
        localDateArray[1] = LocalDate.of(yearChooserEnd.getYear(), monthChooserEnd.getMonth()+1,1);
       return localDateArray;
    }

}
