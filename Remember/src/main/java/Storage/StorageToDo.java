package Storage;

class ToDoContent extends StorageEntry {
    private String name;
    private String description;
    private boolean done;

    public ToDoContent() { }

    private void SetDone(String state) throws Exception {
        if (state.isEmpty())
            throw new Exception();
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
        name = UpdateEntry("", "Name");
        description = UpdateEntry("", "Description");
        String str_done = UpdateEntry((done ? "Yes" : "No"), "Finished");
        SetDone(str_done);
    }

    public void Show() {
        System.out.println("Name:          " + name);
        System.out.println("Description:   " + description);
        System.out.println("Done:          " + (done ? "Yes" : "No"));
    }


    public String GetTile() {
        return "[" + (done ? "X" : " ") + "] " + name;
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
        content.add(entry);
    }
}
