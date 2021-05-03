package Worker;

import Storage.StorageEntry;
import Storage.StorageItem;

/**
 * Interface implemented by both the DataBase and the CSV persistance modules.
 * Allows to perform CRUD on the data.
 */
public interface StorageWorker {
    
    /**
     * Reads all the data from memory.
     * Mainly used for initialization.
     */
    public void ReadAllFromMemory();

    /**
     * Updates an update on a given entry.
     */
    public void UpdateEntry(StorageItem item, StorageEntry before, StorageEntry after);

    /**
     * Deletes an entry.
     */
    public void DeleteEntry(StorageItem item, StorageEntry entry);

    /**
     * Creates a new entry.
     */
    public void CreateEntry(StorageItem item, StorageEntry entry);
}
