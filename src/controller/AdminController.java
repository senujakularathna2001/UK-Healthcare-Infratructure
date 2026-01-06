package controller;

import model.Appointment;

import java.io.*;
import java.util.*;

public class AdminController {

    private final AppointmentController appointmentController = new AppointmentController();

    public List<Appointment> getAllAppointments() {
        return appointmentController.getAllAppointments();
    }

    public List<String[]> getStaff() {
        List<String[]> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data/staff.csv"))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line.split(","));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<String[]> getFacilities() {
        List<String[]> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("data/facilities.csv"))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line.split(","));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void generateReport(String fileName, List<String> lines) {
        try (PrintWriter pw = new PrintWriter("data/" + fileName)) {
            for (String l : lines) {
                pw.println(l);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
