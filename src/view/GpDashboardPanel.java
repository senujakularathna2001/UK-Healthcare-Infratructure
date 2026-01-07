package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GpDashboardPanel extends JPanel {

    public GpDashboardPanel(MainFrame frame) {

        setLayout(new BorderLayout(10,10));
        setBorder(new EmptyBorder(10,10,10,10));

        add(createTop(frame), BorderLayout.NORTH);
        add(createCenter(), BorderLayout.CENTER);
    }

    private JPanel createTop(MainFrame frame) {
        JPanel p = new JPanel(new BorderLayout());

        JLabel title = new JLabel("GP Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> frame.showLogin());

        p.add(title, BorderLayout.WEST);
        p.add(logout, BorderLayout.EAST);
        return p;
    }

    private JTabbedPane createCenter() {
        JTabbedPane tabs = new JTabbedPane();

        tabs.add("Appointments", new GpAppointmentPanel());
        tabs.add("Referrals", new ReferralPanel());
        tabs.add("Prescriptions", new PrescriptionPanel());

        return tabs;
    }
}
