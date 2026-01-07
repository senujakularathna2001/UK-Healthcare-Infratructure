package view;

import controller.*;
import model.*;
import model.referral.ReferralManager;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Map;

public class ReferralPanel extends JPanel {

    private final PatientController patientController = new PatientController();
    private final ClinicianController clinicianController = new ClinicianController();
    private final FacilityController facilityController = new FacilityController();
    private final ReferralController referralController = new ReferralController();

    public ReferralPanel() {

        setLayout(new GridLayout(0, 2, 10, 10));

        JComboBox<String> patientBox = new JComboBox<>();
        JComboBox<String> specialistBox = new JComboBox<>();
        JComboBox<String> facilityBox = new JComboBox<>();

        JTextField reasonField = new JTextField();
        JTextArea notesArea = new JTextArea(3, 20);

        JButton createBtn = new JButton("Create Referral");

        for (String id : patientController.getAllPatientIds()) {
            Patient p = patientController.getPatientById(id);
            patientBox.addItem(p.getPatientId() + " - " + p.getFirstName() + " " + p.getLastName());
        }

        for (Map.Entry<String, String> e : clinicianController.getClinicianIdNameMap().entrySet()) {
            specialistBox.addItem(e.getKey() + " - " + e.getValue());
        }

        for (String name : facilityController.getFacilityNames()) {
            facilityBox.addItem(name);
        }

        add(new JLabel("Patient"));
        add(patientBox);

        add(new JLabel("Specialist"));
        add(specialistBox);

        add(new JLabel("Facility"));
        add(facilityBox);

        add(new JLabel("Reason"));
        add(reasonField);

        add(new JLabel("GP Notes"));
        add(new JScrollPane(notesArea));

        add(new JLabel());
        add(createBtn);

        createBtn.addActionListener(e -> {

            String reason = reasonField.getText().trim();
            String notes = notesArea.getText().trim();

            if (reason.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Reason is required");
                return;
            }

            String referralId = referralController.getNextReferralId();
            String patientId = patientBox.getSelectedItem().toString().split(" ")[0];
            String specialistId = specialistBox.getSelectedItem().toString().split(" ")[0];
            String facilityId = facilityController.getFacilityIdByName(facilityBox.getSelectedItem().toString());

            Referral r = new Referral(referralId, patientId, "GP", specialistId, facilityId, reason, notes, "", "PENDING", LocalDate.now().toString());

            ReferralManager.getInstance().submitReferral(r);

            JOptionPane.showMessageDialog(this, "Referral Created");

            reasonField.setText("");
            notesArea.setText("");
        });
    }
}
