package view;

import controller.*;
import model.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminPanel extends JPanel {

    private final AdminController adminController = new AdminController();
    private final ClinicianController clinicianController = new ClinicianController();
    private final FacilityController facilityController = new FacilityController();

    private JTable appointmentTable;
    private DefaultTableModel appointmentModel;

    public AdminPanel(MainFrame frame) {

        setLayout(new BorderLayout(10,10));
        setBorder(new EmptyBorder(10,10,10,10));

        add(createTop(frame), BorderLayout.NORTH);
        add(createAppointmentsPanel(), BorderLayout.CENTER);
        add(createActions(), BorderLayout.SOUTH);
    }

    private JPanel createTop(MainFrame frame) {
        JPanel p = new JPanel(new BorderLayout(10,0));

        JLabel title = new JLabel("Admin Dashboard");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> frame.showLogin());

        p.add(title, BorderLayout.WEST);
        p.add(logout, BorderLayout.EAST);
        return p;
    }

    private JPanel createAppointmentsPanel() {

        appointmentModel = new DefaultTableModel(
                new String[]{
                        "ID","Patient","Clinician","Facility",
                        "Date","Time","Duration","Type","Status"
                },0
        );

        appointmentTable = new JTable(appointmentModel);
        appointmentTable.setRowHeight(22);
        appointmentTable.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        loadAppointments();

        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(new TitledBorder("Manage Appointments"));
        p.add(new JScrollPane(appointmentTable), BorderLayout.CENTER);
        return p;
    }

    private JPanel createActions() {

        JButton refresh = new JButton("Refresh");
        JButton save = new JButton("Save Changes");
        JButton cancel = new JButton("Cancel Appointment");
        JButton staff = new JButton("View Staff");
        JButton facilities = new JButton("View Facilities");
        JButton report = new JButton("Generate Report");

        refresh.addActionListener(e -> loadAppointments());
        save.addActionListener(e -> saveAppointment());
        cancel.addActionListener(e -> cancelAppointment());
        staff.addActionListener(e -> showStaffTable());
        facilities.addActionListener(e -> showFacilitiesTable());
        report.addActionListener(e -> generateReport());

        JPanel p = new JPanel();
        p.add(refresh);
        p.add(save);
        p.add(cancel);
        p.add(staff);
        p.add(facilities);
        p.add(report);
        return p;
    }

    private void loadAppointments() {
        appointmentModel.setRowCount(0);
        for (Appointment a : adminController.getAllAppointments()) {
            appointmentModel.addRow(new Object[]{
                    a.getAppointmentID(),
                    a.getPatientID(),
                    clinicianController.getClinicianName(a.getClinicianID()),
                    facilityController.getFacilityName(a.getFacilityID()),
                    a.getAppointmentDate(),
                    a.getAppointmentTime(),
                    a.getDurationMinutes(),
                    a.getType(),
                    a.getStatus()
            });
        }
    }

    private void saveAppointment() {

        int row = appointmentTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select an appointment to save");
            return;
        }

        Appointment a = new Appointment(
                appointmentModel.getValueAt(row, 0).toString(),
                appointmentModel.getValueAt(row, 1).toString(),
                clinicianController.getClinicianIdByName(
                        appointmentModel.getValueAt(row, 2).toString()
                ),
                facilityController.getFacilityIdByName(
                        appointmentModel.getValueAt(row, 3).toString()
                ),
                appointmentModel.getValueAt(row, 4).toString(),
                appointmentModel.getValueAt(row, 5).toString(),
                Integer.parseInt(appointmentModel.getValueAt(row, 6).toString()),
                appointmentModel.getValueAt(row, 7).toString(),
                appointmentModel.getValueAt(row, 8).toString(), ""
        );

        new AppointmentController().updateAppointment(a);

        JOptionPane.showMessageDialog(this, "Appointment updated successfully");
        loadAppointments();
    }


    private void cancelAppointment() {
        int r = appointmentTable.getSelectedRow();
        if (r == -1) return;
        String id = appointmentModel.getValueAt(r,0).toString();
        new AppointmentController().cancelAppointment(id);
        loadAppointments();
    }

    private void showStaffTable() {

        JDialog d = new JDialog(
                SwingUtilities.getWindowAncestor(this),
                "Staff List",
                Dialog.ModalityType.APPLICATION_MODAL
        );

        DefaultTableModel m = new DefaultTableModel(
                new String[]{"Staff ID","Name","Role","Department","Contact No."},0
        );

        JTable t = new JTable(m);
        t.setRowHeight(22);

        for (String[] s : adminController.getStaff()) {
            m.addRow(new Object[]{
                    s[0],
                    s[1] + " " + s[2],
                    s[3],
                    s[4],
                    s[6]
            });
        }

        d.add(new JScrollPane(t));
        d.setSize(600,400);
        d.setLocationRelativeTo(this);
        d.setVisible(true);
    }


    private void showFacilitiesTable() {

        JDialog d = new JDialog(
                SwingUtilities.getWindowAncestor(this),
                "Facilities",
                Dialog.ModalityType.APPLICATION_MODAL
        );

        DefaultTableModel m = new DefaultTableModel(
                new String[]{"Facility ID","Name","Type","Address","Postcode"},0
        );

        JTable t = new JTable(m);
        t.setRowHeight(22);

        for (String[] f : adminController.getFacilities()) {
            m.addRow(f);
        }

        d.add(new JScrollPane(t));
        d.setSize(700,400);
        d.setLocationRelativeTo(this);
        d.setVisible(true);
    }

    private void generateReport() {
        List<String> lines = adminController.getAllAppointments()
                .stream()
                .map(Appointment::toFileString)
                .toList();

        adminController.generateReport("admin_appointments_report.txt", lines);
        JOptionPane.showMessageDialog(this, "Report Generated");
    }
}
