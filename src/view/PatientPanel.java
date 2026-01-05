package view;

import controller.PatientController;
import model.Patient;

import javax.swing.*;
import java.util.List;

public class PatientPanel extends JPanel {

    public PatientPanel() {
        JButton loadBtn = new JButton("Load Patients");

        loadBtn.addActionListener(e -> {
            List<Patient> patients =
                    PatientController.loadPatients("data/patients.csv");

            JOptionPane.showMessageDialog(this,
                    "Loaded Patients: " + patients.size());
        });

        add(loadBtn);
    }
}
