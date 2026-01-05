package view;

import controller.AppointmentController;
import model.Appointment;

import javax.swing.*;

public class AppointmentPanel extends JPanel {

    private AppointmentController controller = new AppointmentController();

    public AppointmentPanel() {
        JButton createBtn = new JButton("Create Appointment");

        createBtn.addActionListener(e -> {
            Appointment a = new Appointment(
                    "A013",              // appointment ID
                    "P001",              // patient ID
                    "C001",              // clinician ID
                    "F001",              // facility ID (optional, from your CSV)
                    "2026-01-10",         // date
                    "10:00",              // time
                    30,                   // duration
                    "Routine Consultation",
                    "Scheduled"
            );

            controller.addAppointment(a);
            JOptionPane.showMessageDialog(this, "Appointment Created");
        });

        add(createBtn);
    }
}
