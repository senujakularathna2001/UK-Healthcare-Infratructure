package view;

import controller.AppointmentController;
import model.Appointment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AppointmentPanel extends JPanel {

    private final AppointmentController controller = new AppointmentController();
    private JTable table;
    private DefaultTableModel model;

    public AppointmentPanel() {

        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        model = new DefaultTableModel(new String[]{"ID", "Patient", "Clinician", "Facility", "Date", "Time", "Duration", "Type", "Status"}, 0);

        table = new JTable(model);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table.setRowHeight(22);

        add(new JScrollPane(table), BorderLayout.CENTER);

        JButton refresh = new JButton("Refresh");
        JButton save = new JButton("Save Changes");

        refresh.addActionListener(e -> loadAppointments());
        save.addActionListener(e -> saveSelected());

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        buttons.add(Box.createHorizontalGlue());
        buttons.add(save);
        buttons.add(Box.createRigidArea(new Dimension(8, 0)));
        buttons.add(refresh);

        add(buttons, BorderLayout.SOUTH);

        loadAppointments();
    }

    private void loadAppointments() {
        model.setRowCount(0);
        List<Appointment> list = controller.getAllAppointments();

        for (Appointment a : list) {
            model.addRow(new Object[]{a.getAppointmentID(), a.getPatientID(), a.getClinicianID(), a.getFacilityID(), a.getAppointmentDate(), a.getAppointmentTime(), a.getDurationMinutes(), a.getType(), a.getStatus()});
        }
    }

    private void saveSelected() {
        int row = table.getSelectedRow();
        if (row == -1) return;

        Appointment a = new Appointment(model.getValueAt(row, 0).toString(), model.getValueAt(row, 1).toString(), model.getValueAt(row, 2).toString(), model.getValueAt(row, 3).toString(), model.getValueAt(row, 4).toString(), model.getValueAt(row, 5).toString(), Integer.parseInt(model.getValueAt(row, 6).toString()), model.getValueAt(row, 7).toString(), model.getValueAt(row, 8).toString());

        controller.updateAppointment(a);
        JOptionPane.showMessageDialog(this, "Appointment Updated");
    }
}
