package controller;

import model.Appointment;
import util.FileUtil;
import java.util.*;

public class AppointmentController {

    private List<Appointment> appointments = new ArrayList<>();

    public void addAppointment(Appointment a) {
        appointments.add(a);
        FileUtil.appendToFile("data/appointments.csv", a.toFileString());
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }
}
