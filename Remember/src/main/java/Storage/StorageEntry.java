package Storage;

/**
 * Interface used for storing data entries within the storage mediums.
 */
public interface StorageEntry {
    // Returns the title of the entry.
    public String GetTile();

    // Displays the content of the entry.
    public void Show();

    // Prompts a modification of the entry.
    public void Edit();

    // Creates a new entry.
    public void New();
}
