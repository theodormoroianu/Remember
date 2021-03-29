package Worker;

import java.io.Writer;
import java.nio.file.Paths;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Reader;
import Storage.StorageItem;

import java.util.Calendar;
import java.util.List;
import java.nio.file.Files;

public class AuditWorker {
    
    private AuditWorker() {
    }

    private static AuditWorker instance = null;

    public static AuditWorker GetInstance() {
        if (instance == null)
            instance = new AuditWorker();
        return instance;
    }

    private String GetFilePath() {
        String path = System.getProperty("user.home") + "/.Remember/";
        File theDir = new File(path);
        if (!theDir.exists())
            theDir.mkdirs();
        return path + "events.log";
    }

    public void Log(String s) {
        String date = Calendar.getInstance().getTime().toString();
        try (FileWriter fw = new FileWriter(GetFilePath(), true)) {
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(s + ", " + date);
            bw.newLine();
            bw.close();
        }
        catch (Exception e) {
            // Unable to log.
        }
        
    }
}
