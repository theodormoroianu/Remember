package Storage;

import java.util.List;

class ToDoContent extends StorageEntry {
    private String name;
    private String description;
    private boolean done;

    public ToDoContent() { }

    public ToDoContent(String name, String description, boolean done) {
        this.name = name;
        this.description = description;
        this.done = done;
    }

    public StorageEntry Copy() {
        return new ToDoContent(name, description, done);
    }
        
    public void LoadFromArray(String[] data) {
        name = data[0];
        description = data[1];
        done = Boolean.parseBoolean(data[2]);
    }

    public String[] WriteToArray() {
        return new String[] { name, description, String.valueOf(done) };
    }

    private void SetDone(String state) throws Exception {
        if (state.isEmpty())
            throw new Exception("item should be done or pending");
        char letter = state.charAt(0);
        if (letter == 'Y' || letter == 'y' || letter == '1' || letter == 'X' || letter == 'x')
            done = true;
        else
            done = false;
    }

    public void New() throws Exception {
        name = UpdateEntry("", "Name");
        description = UpdateEntry("", "Description");
        String str_done = UpdateEntry("No", "Finished");
        SetDone(str_done);
    }

    public void Edit() throws Exception {
        name = UpdateEntry(name, "Name");
        description = UpdateEntry(description, "Description");
        String str_done = UpdateEntry((done ? "Yes" : "No"), "Finished");
        SetDone(str_done);
    }

    public void Show() {
        System.out.println("Name:          " + name);
        System.out.println("Description:   " + description);
        System.out.println("Done:          " + (done ? "Yes" : "No"));
    }

    @Override
    public String GetPrintableTitle() {
        return "[" + (done ? "X" : " ") + "] " + name;
    }

    public String GetTitle() {
        return name;
    }
}


public class StorageToDo extends StorageItem {
    private StorageToDo() { }

    static StorageToDo instance = null;

    public static StorageToDo GetInstance() {
        if (instance == null)
            instance = new StorageToDo();
        return instance;
    }
    
    public String Name() {
        return "ToDo";
    }

    public String Description() {
        return "Module storing todos";
    }

    protected void New(String[] args) throws Exception {
        if (args.length != 0)
            throw new Exception();
        StorageEntry entry = new ToDoContent();
        entry.New();
        TryUpdateEntries(null, entry);
        content.add(0, entry);
    }

    public void LoadFromArray(List<String[]> data) {
        for (String[] descr : data) {
            StorageEntry entry = new ToDoContent();
            entry.LoadFromArray(descr);
            content.add(0, entry);
        }
    }
}
