package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

    public static void appendToFile(String filePath, String data) {
        try {
            File file = new File(filePath);

            // ✅ Create parent directories if missing
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            FileWriter fw = new FileWriter(file, true);
            fw.write(data + System.lineSeparator());
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
