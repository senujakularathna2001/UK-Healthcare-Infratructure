package view;

import controller.*;
import model.*;

import javax.swing.*;
import java.awt.*;

public class PrescriptionPanel extends JPanel {

    public PrescriptionPanel() {

        setLayout(new GridLayout(0, 2, 10, 10));

        PatientController pc = new PatientController();
        ClinicianController cc = new ClinicianController();

        JComboBox<String> patientBox = new JComboBox<>();
        JTextField drugField = new JTextField();
        JTextField dosageField = new JTextField();

        JButton save = new JButton("Save Prescription");

        for (String id : pc.getAllPatientIds()) {
            Patient p = pc.getPatientById(id);
            patientBox.addItem(p.getPatientId() + " - " + p.getFirstName() + " " + p.getLastName());
        }

        add(new JLabel("Patient"));
        add(patientBox);
        add(new JLabel("Drug"));
        add(drugField);
        add(new JLabel("Dosage (mg)"));
        add(dosageField);
        add(new JLabel());
        add(save);

        save.addActionListener(e -> {

            String drug = drugField.getText().trim();
            String dosageText = dosageField.getText().trim();

            if (drug.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Drug name is required");
                return;
            }

            float dosage;
            try {
                dosage = Float.parseFloat(dosageText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Dosage must be a number");
                return;
            }

            if (dosage <= 0) {
                JOptionPane.showMessageDialog(this, "Dosage must be greater than zero");
                return;
            }

            Prescription p = new Prescription("RX" + System.currentTimeMillis(), drug, dosage, cc.getClinicianIdNameMap().keySet().iterator().next(), patientBox.getSelectedItem().toString().split(" ")[0]);

            p.checkDrugInteractions();
            new PrescriptionController().savePrescription(p);

            JOptionPane.showMessageDialog(this, "Prescription Saved");

            drugField.setText("");
            dosageField.setText("");
        });
    }
}
