package view;

import controller.*;
import model.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SpecialistPanel extends JPanel {

    private final ReferralController referralController = new ReferralController();
    private final PatientController patientController = new PatientController();

    private JTable referralTable;
    private DefaultTableModel referralModel;

    private final String specialistId = "C005";

    public SpecialistPanel(MainFrame frame) {

        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        add(createTop(frame), BorderLayout.NORTH);
        add(createReferralsPanel(), BorderLayout.CENTER);
        add(createActions(), BorderLayout.SOUTH);

        loadReferrals();
    }

    private JPanel createTop(MainFrame frame) {
        JPanel p = new JPanel(new BorderLayout());

        JLabel title = new JLabel("Specialist Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> frame.showLogin());

        p.add(title, BorderLayout.WEST);
        p.add(logout, BorderLayout.EAST);
        return p;
    }

    private JPanel createReferralsPanel() {
        referralModel = new DefaultTableModel(new String[]{"Referral ID", "Patient", "Reason", "Status", "Notes"}, 0);

        referralTable = new JTable(referralModel);
        referralTable.setRowHeight(22);

        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(new TitledBorder("Patient Referrals"));
        p.add(new JScrollPane(referralTable), BorderLayout.CENTER);
        return p;
    }

    private JPanel createActions() {
        JButton update = new JButton("Update Referral");
        JButton refresh = new JButton("Refresh");

        update.addActionListener(e -> updateReferral());
        refresh.addActionListener(e -> loadReferrals());

        JPanel p = new JPanel();
        p.add(update);
        p.add(refresh);
        return p;
    }

    private void loadReferrals() {
        referralModel.setRowCount(0);

        List<Referral> list = referralController.getReferralsForSpecialist(specialistId);

        for (Referral r : list) {
            Patient p = patientController.getPatientById(r.getPatientId());
            referralModel.addRow(new Object[]{r.getReferralId(), p.getFirstName() + " " + p.getLastName(), r.getReason(), r.getStatus(), r.getSpecialistNotes()});
        }
    }

    private void updateReferral() {
        int r = referralTable.getSelectedRow();
        if (r == -1) return;

        String status = JOptionPane.showInputDialog(this, "Status");
        if (status == null) return;

        String notes = JOptionPane.showInputDialog(this, "Clinical Notes");

        Referral updated = referralController.getReferralsForSpecialist(specialistId).get(r);

        updated.setStatus(status);
        updated.setSpecialistNotes(notes == null ? "" : notes);

        referralController.updateReferral(updated);
        loadReferrals();
    }
}
