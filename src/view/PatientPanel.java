package view;

import controller.*;
import model.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class PatientPanel extends JPanel {

    private final PatientController patientController = new PatientController();
    private final AppointmentController appointmentController = new AppointmentController();
    private final FacilityController facilityController = new FacilityController();
    private final ClinicianController clinicianController = new ClinicianController();

    private JComboBox<String> patientSelector;
    private JComboBox<String> gpSurgeryBox;

    private JTextField patientId, nhs, first, last, dob, phone, email, address, postcode, ecName, ecPhone, regDate;
    private JComboBox<String> gender;

    private JTable appointmentTable;
    private DefaultTableModel appointmentTableModel;

    public PatientPanel(MainFrame frame) {

        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        add(createTop(frame), BorderLayout.NORTH);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, createProfile(), createAppointmentsPanel());
        splitPane.setResizeWeight(0.6);
        splitPane.setDividerSize(6);
        splitPane.setBorder(null);

        add(splitPane, BorderLayout.CENTER);

        loadPatients();
    }

    private JPanel createTop(MainFrame frame) {
        JPanel p = new JPanel(new BorderLayout(10, 0));
        JLabel title = new JLabel("Patient Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));

        patientSelector = new JComboBox<>();
        patientSelector.addActionListener(e -> loadPatient());

        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> frame.showLogin());

        p.add(title, BorderLayout.WEST);
        p.add(patientSelector, BorderLayout.CENTER);
        p.add(logout, BorderLayout.EAST);
        return p;
    }

    private JScrollPane createProfile() {
        JPanel p = new JPanel(new GridBagLayout());
        p.setBorder(new TitledBorder("Patient Profile"));

        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(6, 6, 6, 6);
        g.fill = GridBagConstraints.HORIZONTAL;

        patientId = ro();
        nhs = ro();
        regDate = ro();

        first = tf();
        last = tf();
        dob = tf();
        phone = tf();
        email = tf();
        address = tf();
        postcode = tf();
        ecName = tf();
        ecPhone = tf();

        gender = new JComboBox<>(new String[]{"M", "F"});

        gpSurgeryBox = new JComboBox<>();
        facilityController.getGpSurgeries().forEach((id, name) -> gpSurgeryBox.addItem(id + " - " + name));

        int y = 0;
        addRow(p, g, y++, "Patient ID", patientId, "First Name", first);
        addRow(p, g, y++, "Last Name", last, "DOB", dob);
        addRow(p, g, y++, "NHS Number", nhs, "Gender", gender);
        addRow(p, g, y++, "Phone", phone, "Email", email);
        addRow(p, g, y++, "Address", address, "Postcode", postcode);
        addRow(p, g, y++, "Emergency Contact", ecName, "Emergency Phone", ecPhone);
        addRow(p, g, y++, "Registration Date", regDate, "GP Surgery", gpSurgeryBox);

        JButton save = new JButton("Update Profile");
        g.gridx = 1;
        g.gridy = y;
        g.gridwidth = 2;
        p.add(save, g);

        save.addActionListener(e -> savePatient());

        JScrollPane scroll = new JScrollPane(p);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return scroll;
    }

    private JPanel createAppointmentsPanel() {
        JPanel p = new JPanel(new BorderLayout(5, 5));
        p.setBorder(new TitledBorder("Appointments"));

        appointmentTableModel = new DefaultTableModel(new String[]{"ID", "Clinician", "Facility", "Date", "Time", "Duration", "Type", "Status"}, 0);

        appointmentTable = new JTable(appointmentTableModel);
        appointmentTable.setRowHeight(22);

        p.add(new JScrollPane(appointmentTable), BorderLayout.CENTER);

        JButton create = new JButton("Create");
        JButton save = new JButton("Save Changes");
        JButton cancel = new JButton("Cancel");
        JButton refresh = new JButton("Refresh");

        create.addActionListener(e -> createAppointment());
        save.addActionListener(e -> saveAppointment());
        cancel.addActionListener(e -> cancelAppointment());
        refresh.addActionListener(e -> loadAppointments());

        JPanel buttons = new JPanel();
        buttons.add(create);
        buttons.add(save);
        buttons.add(cancel);
        buttons.add(refresh);

        p.add(buttons, BorderLayout.SOUTH);
        return p;
    }

    private void loadPatients() {
        patientController.getAllPatientIds().forEach(patientSelector::addItem);
        if (patientSelector.getItemCount() > 0) patientSelector.setSelectedIndex(0);
    }

    private void loadPatient() {
        Patient p = patientController.getPatientById((String) patientSelector.getSelectedItem());
        patientId.setText(p.getPatientId());
        first.setText(p.getFirstName());
        last.setText(p.getLastName());
        dob.setText(p.getDob());
        nhs.setText(p.getNhsNumber());
        gender.setSelectedItem(p.getGender());
        phone.setText(p.getPhone());
        email.setText(p.getEmail());
        address.setText(p.getAddress());
        postcode.setText(p.getPostcode());
        ecName.setText(p.getEmergencyContactName());
        ecPhone.setText(p.getEmergencyContactNumber());
        regDate.setText(p.getRegistrationDate());
        loadAppointments();
    }

    private void loadAppointments() {
        appointmentTableModel.setRowCount(0);
        List<Appointment> list = appointmentController.getAppointmentsForPatient(patientId.getText());

        for (Appointment a : list) {
            appointmentTableModel.addRow(new Object[]{a.getAppointmentID(), clinicianController.getClinicianName(a.getClinicianID()), facilityController.getFacilityName(a.getFacilityID()), a.getAppointmentDate(), a.getAppointmentTime(), a.getDurationMinutes(), a.getType(), a.getStatus()});
        }
    }

    private void createAppointment() {
        Map<String, String> clinicians = clinicianController.getClinicianIdNameMap();
        JComboBox<String> clinicianBox = new JComboBox<>(clinicians.values().toArray(new String[0]));

        JTextField date = new JTextField();
        JTextField time = new JTextField();
        JTextField duration = new JTextField();
        JTextField type = new JTextField();

        Object[] fields = {"Clinician", clinicianBox, "Date", date, "Time", time, "Duration", duration, "Type", type};

        if (JOptionPane.showConfirmDialog(this, fields, "Create Appointment", JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION)
            return;

        String clinicianName = clinicianBox.getSelectedItem().toString();
        String clinicianId = clinicianController.getClinicianIdByName(clinicianName);
        String facilityId = clinicianController.getFacilityIdForClinician(clinicianId);

        Appointment a = new Appointment(appointmentController.getNextAppointmentId(), patientId.getText(), clinicianId, facilityId, date.getText(), time.getText(), Integer.parseInt(duration.getText()), type.getText(), "Scheduled", "");

        appointmentController.addAppointment(a);
        loadAppointments();
    }

    private void saveAppointment() {
        int r = appointmentTable.getSelectedRow();
        if (r == -1) return;

        String clinicianName = appointmentTableModel.getValueAt(r, 1).toString();
        String clinicianId = clinicianController.getClinicianIdByName(clinicianName);
        String facilityId = clinicianController.getFacilityIdForClinician(clinicianId);

        Appointment a = new Appointment(appointmentTableModel.getValueAt(r, 0).toString(), patientId.getText(), clinicianId, facilityId, appointmentTableModel.getValueAt(r, 3).toString(), appointmentTableModel.getValueAt(r, 4).toString(), Integer.parseInt(appointmentTableModel.getValueAt(r, 5).toString()), appointmentTableModel.getValueAt(r, 6).toString(), appointmentTableModel.getValueAt(r, 7).toString(), "");

        appointmentController.updateAppointment(a);
        loadAppointments();
    }

    private void savePatient() {
        String gpId = gpSurgeryBox.getSelectedItem().toString().split(" - ")[0];

        Patient p = new Patient(patientId.getText(), first.getText(), last.getText(), dob.getText(), nhs.getText(), gender.getSelectedItem().toString(), phone.getText(), email.getText(), address.getText(), postcode.getText(), ecName.getText(), ecPhone.getText(), regDate.getText(), gpId);

        patientController.updatePatient(p);
        JOptionPane.showMessageDialog(this, "Profile Updated");
    }

    private void cancelAppointment() {
        int r = appointmentTable.getSelectedRow();
        if (r == -1) return;
        appointmentController.cancelAppointment(appointmentTableModel.getValueAt(r, 0).toString());
        loadAppointments();
    }

    private JTextField tf() {
        return new JTextField(15);
    }

    private JTextField ro() {
        JTextField t = new JTextField(15);
        t.setEditable(false);
        return t;
    }

    private void addRow(JPanel p, GridBagConstraints g, int y, String l1, JComponent c1, String l2, JComponent c2) {

        g.gridy = y;
        g.gridx = 0;
        p.add(new JLabel(l1), g);
        g.gridx = 1;
        p.add(c1, g);
        g.gridx = 2;
        p.add(new JLabel(l2), g);
        g.gridx = 3;
        p.add(c2, g);
    }
}
