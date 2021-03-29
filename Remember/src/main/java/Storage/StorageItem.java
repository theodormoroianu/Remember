package Storage;

import java.util.ArrayList;
import java.io.PrintStream;

import Worker.AuditWorker;
import Worker.StringWorker;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

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
        titles = new HashSet<>();
        out = System.out;
        additional_commands = new ArrayList <> ();
    }

    protected PrintStream out;
    protected ArrayList <StorageEntry> content;
    protected Set <String> titles;
    protected ArrayList <String> additional_commands;


    /**
     * Executes custom commands.
     */
    protected void CustomCommand(String command, String[] args) throws Exception {
        throw new Exception();
    }

    /**
     * Executes a list of commands received from the command line.
     * @param arguments given by the command line.
     */
    public void Execute(String[] args) {
        
        String total_args = "";
        for (String s : args)
            total_args = total_args + " " + s;
        AuditWorker.GetInstance().Log("Executing commands \"" + total_args
                    + "\" in module " + Name() + "!");

        // Tries to parse the arguments.
        // Throws an exception if something goes wrong.
        try {
            // No arguments provided.
            if (args.length == 0) {
                DisplayHelp();
                return;
            }
            
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
                CustomCommand(s, remaining_args);        
        } catch(Exception e) {
            if (e.getMessage() == null)
                System.out.println("Invalid command. Please use \"" + Name() + " Help\" for help."); 
            else
                System.out.println(
                        "Invalid command: " +
                        e.getMessage() +
                        ". Please use \""+
                        Name() +
                        " Help\" for help."
                ); 

        }
    }

    /**
     * Displays help message.
     */
    private void DisplayHelp() {
        System.out.println("Usage: " + Name() + " [COMMAND] <ARGS>");
        System.out.println("Rɘmɘmbɘr " + Name() + " -- " + Description());
        System.out.println("\nAvailable commands:");
        System.out.println("    * " + Name() + " View    <NAME>");
        System.out.println("    * " + Name() + " Delete  <NAME>");
        System.out.println("    * " + Name() + " Edit    <NAME>");
        System.out.println("    * " + Name() + " New");
        System.out.println("    * " + Name() + " List");
        for (String s : additional_commands)
            System.out.println("    * " + Name() + " " + s);
            
        System.out.println("For more details, please check documentation.");
    }

    /**
     * Creates a new item.
     */
    abstract protected void New(String[] args) throws Exception;

    /**
     * Loads data from an array of lists.
     */
    abstract public void LoadFromArray(List<String[]> data);

    /**
     * Writes data to an array of lists.
     */
    public List<String[]> WriteToArray() {
        List<String[]> data = new ArrayList<>();
        for (StorageEntry e : content) {
            data.add(e.WriteToArray());
        }
        return data;
    }

    /**
     * Finds an entry in the content.
     */
    protected StorageEntry FindEntry(String[] args) throws Exception {
        if (args.length == 0)
            throw new Exception();

        String title = StringWorker.JoinWithSpaces(args);
        StorageEntry entry = null;

        for (StorageEntry s : content)
            if (StringWorker.CheckEqual(s.GetTitle(), title))
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
        StorageEntry entry = FindEntry(args);

        if (entry == null)
            out.println("No entry with the given name was found!");
        else {
            StorageEntry new_entry = entry.Copy();
            new_entry.Edit();
            TryUpdateEntries(entry.GetTitle(), new_entry);
            content.remove(entry);
            content.add(0, new_entry);
        }
    }

    /**
     * Deletes the items from content matching the argument.
     */
    private void Delete(String[] args) throws Exception {
        StorageEntry entry = FindEntry(args);

        if (entry == null)
            out.println("No entry with the given name was found!");
        else {
            TryUpdateEntries(entry.GetTitle(), null);
            content.remove(entry);
        }
    }

    /**
     * Prints all the available items.
     */
    private void List(String[] args) throws Exception {
        if (args.length != 0)
            throw new Exception();
        
        if (content.size() == 0) {
            out.println("No entries found!");
            return;
        }

        for (StorageEntry elem : content)
            out.println(" * " + elem.GetPrintableTitle());
        
        out.println("Found " + content.size() + " entries!");
    }

    /**
     * Updates an entry's title in memory.
     * This function DOES NOT update the actual entry, just the title.
     */
    protected void TryUpdateEntries(String last_title, StorageEntry entry) throws Exception {
        // Delete.
        if (entry == null) {
            titles.remove(StringWorker.Standard(last_title));
            return;
        }

        boolean ok = false;
        if (last_title != null && StringWorker.CheckEqual(last_title, entry.GetTitle()))
            ok = true;
        
        if (!titles.contains(StringWorker.Standard(entry.GetTitle())))
            ok = true;
        
        if (!ok)
            throw new Exception("name is already used");
        
        if (last_title != null)
            titles.remove(StringWorker.Standard(last_title));
        
        titles.add(StringWorker.Standard(entry.GetTitle()));
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
