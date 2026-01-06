package view;

import controller.*;
import model.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class NursePanel extends JPanel {

    private final PatientController patientController = new PatientController();
    private final AppointmentController appointmentController = new AppointmentController();

    private JComboBox<String> patientSelector;
    private JTable table;
    private DefaultTableModel model;

    public NursePanel(MainFrame frame) {

        setLayout(new BorderLayout(10,10));
        setBorder(new EmptyBorder(10,10,10,10));

        add(createTop(frame), BorderLayout.NORTH);
        add(createTable(), BorderLayout.CENTER);
        add(createActions(), BorderLayout.SOUTH);

        loadPatients();
    }

    private JPanel createTop(MainFrame frame) {
        JPanel p = new JPanel(new BorderLayout(10,0));

        JLabel title = new JLabel("Nurse Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));

        patientSelector = new JComboBox<>();
        patientSelector.addActionListener(e -> loadAppointments());

        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> frame.showLogin());

        p.add(title, BorderLayout.WEST);
        p.add(patientSelector, BorderLayout.CENTER);
        p.add(logout, BorderLayout.EAST);
        return p;
    }

    private JScrollPane createTable() {
        model = new DefaultTableModel(
                new String[]{"Appointment ID","Date","Time","Type","Status","Treatment Notes"},0
        );

        table = new JTable(model);
        table.setRowHeight(24);

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(new TitledBorder("Patient Appointments"));
        return sp;
    }

    private JPanel createActions() {
        JButton save = new JButton("Save Treatment Notes");
        save.addActionListener(e -> saveNotes());

        JPanel p = new JPanel();
        p.add(save);
        return p;
    }

    private void loadPatients() {
        patientController.getAllPatientIds().forEach(patientSelector::addItem);
        if (patientSelector.getItemCount() > 0)
            patientSelector.setSelectedIndex(0);
    }

    private void loadAppointments() {
        model.setRowCount(0);
        List<Appointment> list =
                appointmentController.getAppointmentsForPatient(
                        patientSelector.getSelectedItem().toString()
                );

        for (Appointment a : list) {
            model.addRow(new Object[]{
                    a.getAppointmentID(),
                    a.getAppointmentDate(),
                    a.getAppointmentTime(),
                    a.getType(),
                    a.getStatus(),
                    a.getNotes()
            });
        }
    }

    private void saveNotes() {
        int r = table.getSelectedRow();
        if (r == -1) return;

        String id = model.getValueAt(r,0).toString();
        String notes = model.getValueAt(r,5).toString();

        appointmentController.updateAppointmentNotes(id, notes);
        JOptionPane.showMessageDialog(this,"Treatment notes updated");
    }
}
