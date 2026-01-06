package controller;

import java.io.*;
import java.util.*;

public class ClinicianController {

    private static final String FILE = "data/clinicians.csv";

    public Map<String, String> getClinicianIdNameMap() {
        Map<String, String> map = new LinkedHashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                map.put(d[0], d[1] + " " + d[2]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public String getClinicianIdByName(String name) {
        for (Map.Entry<String, String> e : getClinicianIdNameMap().entrySet()) {
            if (e.getValue().equals(name)) return e.getKey();
        }
        return null;
    }

    public String getClinicianName(String clinicianId) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/clinicians.csv"))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d[0].equals(clinicianId)) {
                    return d[1] + " " + d[2];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clinicianId;
    }

    public String getFacilityIdForClinician(String clinicianId) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d[0].equals(clinicianId)) return d[4];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
