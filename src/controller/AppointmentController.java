package controller;

import model.Appointment;

import java.io.*;
import java.util.*;

public class AppointmentController {

    private static final String FILE = "data/appointments.csv";

    public List<Appointment> getAllAppointments() {
        List<Appointment> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] d = line.split(",", -1);
                list.add(new Appointment(
                        d[0], d[1], d[2], d[3],
                        d[4], d[5],
                        Integer.parseInt(d[6]),
                        d[7], d[8], d[9]
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Appointment> getAppointmentsForPatient(String patientId) {
        List<Appointment> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d[1].equals(patientId)) {
                    list.add(new Appointment(
                            d[0], d[1], d[2], d[3],
                            d[4], d[5],
                            Integer.parseInt(d[6]),
                            d[7], d[8], d.length > 9 ? d[9] : ""
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addAppointment(Appointment a) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE, true))) {
            pw.println(a.toFileString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAppointment(Appointment updated) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(updated.getAppointmentID() + ",")) {
                    lines.add(updated.toFileString());
                } else {
                    lines.add(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter(FILE)) {
            for (String l : lines) pw.println(l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getNextAppointmentId() {
        int max = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String id = line.split(",")[0];
                int n = Integer.parseInt(id.substring(1));
                if (n > max) max = n;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.format("A%03d", max + 1);
    }

    public void cancelAppointment(String appointmentId) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(appointmentId + ",")) {
                    String[] d = line.split(",");
                    d[8] = "Cancelled";
                    line = String.join(",", d);
                }
                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter(FILE)) {
            for (String l : lines) pw.println(l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateAppointmentNotes(String appointmentId, String notes) {

        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("data/appointments.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(appointmentId + ",")) {
                    String[] d = line.split(",", -1);
                    d[d.length - 1] = notes.replace(",", " ");
                    line = String.join(",", d);
                }
                lines.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter("data/appointments.csv")) {
            for (String l : lines) pw.println(l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
