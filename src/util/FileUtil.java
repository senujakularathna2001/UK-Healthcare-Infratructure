package util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static void appendToFile(String filePath, String data) {
        try {
            File file = new File(filePath);

            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write(data + System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateAppointmentStatus(
            String filePath, String appointmentId, String newStatus) {

        File file = new File(filePath);
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.startsWith(appointmentId + ",")) {
                    String[] d = line.split(",");
                    d[8] = newStatus;
                    line = String.join(",", d);
                }
                lines.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter pw = new PrintWriter(file)) {
            for (String l : lines) {
                pw.println(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
