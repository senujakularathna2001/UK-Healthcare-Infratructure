package controller;

import model.Patient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PatientController {

    private static final String FILE = "data/patients.csv";

    public Patient getPatientById(String patientId) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] d = line.split(",", -1);
                if (d.length < 14) continue;

                if (d[0].equals(patientId)) {
                    return new Patient(
                            d[0],
                            d[1],
                            d[2],
                            d[3],
                            d[4],
                            d[5],
                            d[6],
                            d[7],
                            d[8],
                            d[9],
                            d[10],
                            d[11],
                            d[12],
                            d[13]
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public List<String> getAllPatientIds() {
        List<String> ids = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                ids.add(line.split(",")[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ids;
    }

    public void updatePatient(Patient p) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(p.getPatientId() + ",")) {
                    lines.add(p.toCsv());
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
}
