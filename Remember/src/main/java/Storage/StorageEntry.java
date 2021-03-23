package Storage;

import Worker.*;

/**
 * Interface used for storing data entries within the storage mediums.
 */
public abstract class StorageEntry {
    // Returns the title of the entry.
    public abstract String GetTitle();

    // Returns the title to display.
    public String GetPrintableTitle() {
        return GetTitle();
    }
    
    // Displays the content of the entry.
    public abstract void Show();

    // Loads from a list.
    public abstract void LoadFromArray(String[] data);

    // Prints to a list.
    public abstract String[] WriteToArray();
    
    // Prompts a modification of the entry.
    public abstract void Edit() throws Exception;

    // Creates a new entry.
    public abstract void New() throws Exception;

    // Reads something from stdin.
    protected String UpdateEntry(String old_value, String description) throws Exception {
        if (old_value.length() != 0)
            System.out.print(description + " [" + old_value + "]: ");
        else
            System.out.print(description + ": ");
        String new_name = IOWorker.GetInstance().GetLine();
        if (!new_name.isEmpty())
            return new_name;
        if (!old_value.isEmpty())
            return old_value;
        throw new Exception("field \"" + description + "\" should not be empty");
    }

    /**
     * Returns a copy of the object.
     */
    public abstract StorageEntry Copy();
}
