package Worker;

import java.io.Writer;
import java.nio.file.Paths;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.File;
import java.io.Reader;
import Storage.StorageItem;
import java.util.List;
import java.nio.file.Files;

public class PersistenceWorker {
    
    private PersistenceWorker() {
    }

    private static PersistenceWorker instance = null;

    public static PersistenceWorker GetInstance() {
        if (instance == null)
            instance = new PersistenceWorker();
        return instance;
    }

    private String GetFolderPath() {
        String path = System.getProperty("user.home") + "/.Remember/";
        File theDir = new File(path);
        if (!theDir.exists())
            theDir.mkdirs();
        return path;
    }

    public void SaveToMemory() {
        for (StorageItem item : ItemsWorker.GetInstance().GetStorageItems()) {
            try {
                Writer writer = Files.newBufferedWriter(
                    Paths.get(GetFolderPath() + item.Name() + ".csv")
                );

                CSVWriter csvwriter = new CSVWriter(writer);
                csvwriter.writeAll(item.WriteToArray());
                csvwriter.close();
                writer.close();
            }
            catch (Exception e) {
                // System.out.println("Unable to save item " + item.Name() + " to memory!");
                // e.printStackTrace();
            }
        }
    }

    public void LoadFromMemory() {
        for (StorageItem item : ItemsWorker.GetInstance().GetStorageItems()) {
            try {
                Reader reader = Files.newBufferedReader(
                    Paths.get(GetFolderPath() + item.Name() + ".csv")
                );

                CSVReader csvreader = new CSVReader(reader);
                List<String[]> data = csvreader.readAll();

                item.LoadFromArray(data);

                csvreader.close();
                reader.close();
            }
            catch (Exception e) {
                // System.out.println("Unable to load item " + item.Name() + " to memory!");
                // e.printStackTrace();
            }
        }
    }
}
