package view;

import javax.swing.*;

public class MainFrame extends JFrame {

    private JTabbedPane tabs = new JTabbedPane();

    public MainFrame() {
        setTitle("Hospital Management System");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(new LoginPanel(this));
        setVisible(true);
    }

    public void setRole(String role) {
        getContentPane().removeAll();
        tabs.removeAll();

        if (role.equals("Patient")) {
            tabs.add("Patient", new PatientPanel(this));
        } else if (role.equals("GP")) {
            tabs.add("Referrals", new ReferralPanel());
            tabs.add("Prescriptions", new PrescriptionPanel());
        } else if (role.equals("Specialist")) {
            tabs.add("View Referrals", new ReferralPanel());
        } else if (role.equals("Nurse")) {
            tabs.add("Treatment Notes", new NursePanel());
        } else if (role.equals("Admin")) {
            tabs.add("Admin", new AdminPanel());
            tabs.add("Appointments", new AppointmentPanel());
        }

        add(tabs);
        revalidate();
        repaint();
    }

    public void showLogin() {
        getContentPane().removeAll();
        add(new LoginPanel(this));
        revalidate();
        repaint();
    }
}
