package Worker;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import Storage.StorageEntry;
import Storage.StorageItem;

/**
 * Database engine.
 * 
 * For each storage entry, there is an apropriate table.
 * See function "CreateTables" to see a list of tables.
 */
public class DatabaseWorker implements StorageWorker {
    private DatabaseWorker() { }

    private static DatabaseWorker instance = null;

    public static DatabaseWorker GetInstance() {
        if (instance == null)
            instance = new DatabaseWorker();
        return instance;
    }

    private static String user = "remember";
    private static String password = "remember";
    private static String url = "jdbc:mysql://localhost:3306/db_remember";
    
    public void InitializeTables() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
                    Statement statement = connection.createStatement()) {
                
                for (StorageItem item : ItemsWorker.GetInstance().GetStorageItems()) {
                    String query = "CREATE TABLE IF NOT EXISTS " + item.Name() + " ( "
                                    + "     name VARCHAR(100),"
                                    + "     content VARCHAR(300)"
                                    + " )";
                    statement.executeUpdate(query);
                }
        }
        catch (Exception e) {
            AuditWorker.GetInstance().Log("Unable to initialize tables!");
        }
    }


    public void ReadAllFromMemory() {
        AuditWorker.GetInstance().Log("Loading items from DB");
        InitializeTables();

        try (Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()) {
                
            for (StorageItem item : ItemsWorker.GetInstance().GetStorageItems()) {
                String query = "SELECT content FROM " + item.Name();
                ResultSet results = statement.executeQuery(query);
                List<String[]> db_content = new ArrayList<>();

                // iterate through all the lines of the table.
                while (results.next()) {
                    // retreive the content, and parse it as a csv.
                    String content = results.getString(1);
                    
                    Reader reader = new StringReader(content);
                    CSVReader csvreader = new CSVReader(reader);
                    String[] value = csvreader.readNext();
                    db_content.add(value);
                    csvreader.close();
                    reader.close();
                }

                item.LoadFromArray(db_content);
            }
        }
        catch (Exception e) {
            AuditWorker.GetInstance().Log("Unable to retreive data!");
        }
    }

    /**
     * Converts an entry into a string (via CSVWriter).
     */
    private String EntryToString(StorageEntry entry) throws Exception {
        Writer writer = new StringWriter();
        CSVWriter csvwriter = new CSVWriter(writer);
        csvwriter.writeNext(entry.WriteToArray());
        csvwriter.close();
        return writer.toString();
    }

    public void UpdateEntry(StorageItem item, StorageEntry before, StorageEntry after) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()) {
            String query = "UPDATE " + item.Name()
                            + " SET name=" + "'" + after.GetTitle() + "',"
                            + "     content=" + "'" + EntryToString(after) + "'"
                            + " WHERE name=" + "'" + before.GetTitle() + "'";
            statement.executeUpdate(query);
        }
        catch (Exception e) {
            AuditWorker.GetInstance().Log("Unable to update entry in " + item.Name() + "!");
        }
    }

    public void DeleteEntry(StorageItem item, StorageEntry entry) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()) {
            String query = "DELETE FROM " + item.Name()
                            + " WHERE name=" + "'" + entry.GetTitle() + "'";
            statement.executeUpdate(query);
        }
        catch (Exception e) {
            AuditWorker.GetInstance().Log("Unable to delete entry in " + item.Name() + "!");
        }
    }

    public void CreateEntry(StorageItem item, StorageEntry entry) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()) {
            String query = "INSERT INTO " + item.Name()
                            + " VALUES('" + entry.GetTitle() + "', "
                            + "'" + EntryToString(entry) + "')";
            statement.executeUpdate(query);
        }
        catch (Exception e) {
            AuditWorker.GetInstance().Log("Unable to create entry in " + item.Name() + "!");
        }
    }
}
