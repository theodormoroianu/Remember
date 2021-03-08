package Storage;

import java.util.ArrayList;

/**
 * Interface implemented by storage items:
 *  - Math formulas
 *  - Text
 *  - Contacts
 *  - URLs
 *  - Checklists
 */
public interface StorageItem {
    /**
     * Loads the storage class from a file.
     * @param string with the path of the file to read.
     */
    public void Load(String path);
    
    /**
     * Writes the storage class to a file.
     * @param string with the path of the file to read.
     */
    public void Unload(String path);

    /**
     * Executes a list of commands received from the command line.
     * @param arguments given by the command line.
     */
    public void Execute(String[] args);

    /**
     * Returns the name of the component.
     * @return String with the name of the component.
     */
    public String Name();

    /**
     * Returns a quick description of the component.
     * @return String with a quick description of the component.
     */
    public String Description();
}
