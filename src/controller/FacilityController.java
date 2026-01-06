package controller;

import java.io.*;
import java.util.*;

public class FacilityController {

    private static final String FILE = "data/facilities.csv";

    public Map<String, String> getGpSurgeries() {
        Map<String, String> map = new LinkedHashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");

                String type = d[2].toLowerCase();

                if (type.contains("gp")) {
                    map.put(d[0], d[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public String getFacilityName(String id) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/facilities.csv"))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d[0].equals(id)) return d[1];
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public String getFacilityIdByName(String name) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(",");
                if (d[1].equalsIgnoreCase(name)) {
                    return d[0];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }
}
