package view;

import controller.*;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Map;

public class GpAppointmentPanel extends JPanel {

    private final AppointmentController appointmentController = new AppointmentController();
    private final PatientController patientController = new PatientController();
    private final ClinicianController clinicianController = new ClinicianController();
    private final FacilityController facilityController = new FacilityController();

    public GpAppointmentPanel() {

        setLayout(new GridLayout(0, 2, 10, 10));

        JComboBox<String> patientBox = new JComboBox<>();
        JComboBox<String> clinicianBox = new JComboBox<>();
        JComboBox<String> facilityBox = new JComboBox<>();

        JTextField dateField = new JTextField(LocalDate.now().toString());
        JTextField timeField = new JTextField("09:00");
        JTextField durationField = new JTextField("30");
        JTextField typeField = new JTextField("GP Consultation");

        JButton createBtn = new JButton("Create Appointment");

        for (String id : patientController.getAllPatientIds()) {
            Patient p = patientController.getPatientById(id);
            patientBox.addItem(p.getPatientId() + " - " + p.getFirstName() + " " + p.getLastName());
        }

        for (Map.Entry<String, String> e : clinicianController.getClinicianIdNameMap().entrySet()) {
            clinicianBox.addItem(e.getKey() + " - " + e.getValue());
        }

        for (String f : facilityController.getFacilityNames()) {
            facilityBox.addItem(f);
        }

        add(new JLabel("Patient"));
        add(patientBox);
        add(new JLabel("Clinician"));
        add(clinicianBox);
        add(new JLabel("Facility"));
        add(facilityBox);
        add(new JLabel("Date"));
        add(dateField);
        add(new JLabel("Time"));
        add(timeField);
        add(new JLabel("Duration (min)"));
        add(durationField);
        add(new JLabel("Type"));
        add(typeField);
        add(new JLabel());
        add(createBtn);

        createBtn.addActionListener(e -> {
            String appointmentId = appointmentController.getNextAppointmentId();

            String patientId = patientBox.getSelectedItem().toString().split(" ")[0];
            String clinicianId = clinicianBox.getSelectedItem().toString().split(" ")[0];
            String facilityId = facilityController.getFacilityIdByName(facilityBox.getSelectedItem().toString());

            Appointment a = new Appointment(appointmentId, patientId, clinicianId, facilityId, dateField.getText(), timeField.getText(), Integer.parseInt(durationField.getText()), typeField.getText(), "BOOKED", "");

            appointmentController.addAppointment(a);
            JOptionPane.showMessageDialog(this, "Appointment Created");
        });
    }
}
