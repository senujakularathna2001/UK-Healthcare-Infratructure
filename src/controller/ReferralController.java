package controller;

import model.Referral;

import java.io.*;
import java.util.*;

public class ReferralController {

    private static final String FILE = "data/referrals.csv";

    public List<Referral> getReferralsForSpecialist(String specialistId) {
        List<Referral> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] d = line.split(",", -1);
                if (d[3].equals(specialistId)) {
                    list.add(new Referral(d[0], d[1], d[2], d[3], d[4], d[5], d[6], d[7], d[8], d[9]));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addReferral(Referral r) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE, true))) {
            pw.println(r.toCsv());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateReferral(Referral updated) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(updated.getReferralId() + ",")) {
                    lines.add(updated.toCsv());
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

    public String getNextReferralId() {
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
        return String.format("R%03d", max + 1);
    }
}
