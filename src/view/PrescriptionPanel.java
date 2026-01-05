package view;

import controller.PrescriptionController;
import model.Prescription;

import javax.swing.*;

public class PrescriptionPanel extends JPanel {

    public PrescriptionPanel() {
        JButton btn = new JButton("Create Prescription");

        btn.addActionListener(e -> {
            Prescription p = new Prescription(
                    "RX001",
                    "Aspirin",
                    50f,
                    "C001",
                    "P001"
            );

            p.checkDrugInteractions();
            new PrescriptionController().savePrescription(p);

            JOptionPane.showMessageDialog(this, "Prescription Saved");
        });

        add(btn);
    }
}
