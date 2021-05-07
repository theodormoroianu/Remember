package Worker;

import java.io.File;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import Storage.StorageEntry;
import Storage.StorageItem;

/**
 * Saves files to CSV.
 * When updating an entry, just re-save the whole CSV file.
 */
public class CSVWorker implements StorageWorker {
    
    private CSVWorker() {
    }

    private static CSVWorker instance = null;

    public static CSVWorker GetInstance() {
        if (instance == null)
            instance = new CSVWorker();
        return instance;
    }

    private String GetFolderPath() {
        String path = System.getProperty("user.home") + "/.Remember/";
        File theDir = new File(path);
        if (!theDir.exists())
            theDir.mkdirs();
        return path;
    }

    public void SaveToMemory(StorageItem item) {
        AuditWorker.GetInstance().Log("Saving items to memory");
        try (Writer writer = Files.newBufferedWriter(
                Paths.get(GetFolderPath() + item.Name() + ".csv"))) {

            CSVWriter csvwriter = new CSVWriter(writer);
            csvwriter.writeAll(item.WriteToArray());
            csvwriter.close();
        }
        catch (Exception e) {
            AuditWorker.GetInstance().Log("Received error " + e.getMessage()
                    + " when saving csvs to memory");  
        }
    }

    public void ReadAllFromMemory() {
        AuditWorker.GetInstance().Log("Loading items from memory");
        for (StorageItem item : ItemsWorker.GetInstance().GetStorageItems()) {
            try (Reader reader = Files.newBufferedReader(
                    Paths.get(GetFolderPath() + item.Name() + ".csv"))) {
                CSVReader csvreader = new CSVReader(reader);
                List<String[]> data = csvreader.readAll();

                item.LoadFromArray(data);

                csvreader.close();
            }
            catch (Exception e) {
                AuditWorker.GetInstance().Log("Received error " + e.getMessage()
                        + " when loading csvs from memory!");
            }
        }
    }

    public void UpdateEntry(StorageItem item, StorageEntry before, StorageEntry after) {
        SaveToMemory(item);
    }

    public void DeleteEntry(StorageItem item, StorageEntry entry) {
        SaveToMemory(item);
    }

    public void CreateEntry(StorageItem item, StorageEntry entry) {
        SaveToMemory(item);
    }
}
