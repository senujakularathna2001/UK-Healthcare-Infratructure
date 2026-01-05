package controller;

import model.Patient;
import java.io.*;
import java.util.*;

public class PatientController {

    public static List<Patient> loadPatients(String file) {
        List<Patient> patients = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                patients.add(new Patient(
                        data[0],
                        data[1],
                        data[2],
                        data[3]
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }
}
