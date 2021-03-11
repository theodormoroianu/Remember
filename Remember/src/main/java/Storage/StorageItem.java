package Storage;

import java.util.ArrayList;
import java.io.PrintStream;
import Worker.StringWorker;
import java.util.Arrays;

/**
 * Abstract class implementing a behaviour we need in
 * classes storing intormations like:
 *  - Math formulas
 *  - Text
 *  - Contacts
 *  - URLs
 *  - Checklists
 */
public abstract class StorageItem {
    
    StorageItem() {
        content = new ArrayList <> ();
        out = System.out;
    }

    protected PrintStream out;
    protected ArrayList <StorageEntry> content;

    /**
     * Executes a list of commands received from the command line.
     * @param arguments given by the command line.
     */
    public void Execute(String[] args) {
        
        // Tries to parse the arguments.
        // Throws an exception if something goes wrong.
        try {
            // No arguments provided.
            if (args.length == 0)
                throw new Exception();
            
            // Command received.
            String s = args[0];
            // Commands to pass forward.
            String[] remaining_args = Arrays.copyOfRange(args, 1, args.length);

            if (StringWorker.CheckEqual(s, "List"))
                List(remaining_args);
            else if (StringWorker.CheckEqual(s, "View"))
                View(remaining_args);
            else if (StringWorker.CheckEqual(s, "Delete"))
                Delete(remaining_args);
            else if (StringWorker.CheckEqual(s, "Edit"))
                Edit(remaining_args);
            else if (StringWorker.CheckEqual(s, "New"))
                New(remaining_args);
            else if (StringWorker.CheckEqual(s, "Help"))
                DisplayHelp();
            else
                throw new Exception(); // Unrecognized command        
        } catch(Exception e) {
            System.out.println("Invalid command. Please use \"" + Name() + " Help\" for help."); 
        }
    }

    /**
     * Displays help message.
     */
    private void DisplayHelp() {
        System.out.println("Usage: " + Name() + " [COMMAND] <ARGS>");
        System.out.println("Rɘmɘmbɘr " + Name() + " -- " + Description());
        System.out.println("\nAvailable commands:");
        System.out.println("    * " + Name() + " View   <NAME>");
        System.out.println("    * " + Name() + " Delete <NAME>");
        System.out.println("    * " + Name() + " Edit   <NAME>");
        System.out.println("    * " + Name() + " New");
        System.out.println("    * " + Name() + " List");
        System.out.println("For more details, please check documentation.");
    }

    /**
     * Creates a new item.
     */
    abstract protected void New(String[] args) throws Exception;

    /**
     * Finds an entry in the content.
     */
    StorageEntry FindEntry(String[] args) throws Exception {
        if (args.length == 0)
            throw new Exception();

        String title = StringWorker.JoinWithSpaces(args);
        StorageEntry entry = null;

        for (StorageEntry s : content)
            if (StringWorker.CheckEqual(s.GetTile(), title))
                entry = s;

        return entry;
    }

    /**
     * Prints the items from content matching the argument.
     */
    private void View(String[] args) throws Exception {
        StorageEntry entry = FindEntry(args);

        if (entry == null)
            out.println("No entry with the given name was found!");
        else
            entry.Show();
    }

    /**
     * Edits the item from content matching the argument.
     */
    private void Edit(String[] args) throws Exception {
        StorageEntry entry = FindEntry(args); // TODO: not be able to have same title twice

        if (entry == null)
            out.println("No entry with the given name was found!");
        else
            entry.Edit();
    }

    /**
     * Deletes the items from content matching the argument.
     */
    private void Delete(String[] args) throws Exception {
        StorageEntry entry = FindEntry(args);

        if (entry == null)
            out.println("No entry with the given name was found!");
        else
            content.remove(entry);
    }

    /**
     * Prints all the available items.
     */
    private void List(String[] args) throws Exception {
        if (args.length != 0)
            throw new Exception();
        
        if (content.size() == 0) {
            out.println("No entries found!");
        }

        for (StorageEntry elem : content)
            out.println(" * " + elem.GetTile());
        
        out.println("Found " + content.size() + " entries!");
    }



    /**
     * Returns the name of the component.
     * @return String with the name of the component.
     */
    abstract public String Name();

    /**
     * Returns a quick description of the component.
     * @return String with a quick description of the component.
     */
    abstract public String Description();
}
