package controller;

import model.Patient;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PatientController {

    private static final String FILE = "data/patients.csv";

    public Patient getPatientById(String patientId) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            br.readLine(); // header
            String line;

            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");

                if (d[0].equals(patientId)) {
                    return new Patient(
                            d[0],  // patient_id
                            d[4],  // nhs_number
                            d[1],  // first_name
                            d[2],  // last_name
                            d[3],  // dob
                            d[5],  // gender
                            d[6],  // phone
                            d[7],  // email
                            d[8],  // address
                            d[10],  // postcode
                            d[11], // emergency_contact_name
                            d[12], // emergency_contact_number
                            d[13], // registration_date
                            d[14]  // gp_surgery_id
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
