package Storage;

import java.util.List;

class DictContent extends StorageEntry {
    private String name;
    private String description;

    public DictContent() { }

    public DictContent(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public StorageEntry Copy() {
        return new DictContent(name, description);
    }
        
    public void LoadFromArray(String[] data) {
        name = data[0];
        description = data[1];
    }

    public String[] WriteToArray() {
        return new String[] { name, description };
    }

    public void New() throws Exception {
        name = UpdateEntry("", "Word");
        description = UpdateEntry("-", "Definition");
    }

    public void Edit() throws Exception {
        name = UpdateEntry(name, "Word");
        description = UpdateEntry(description, "Definition");
    }

    public void Show() {
        System.out.println("Word:        " + name);
        System.out.println("Definition:  " + description);
    }

    public String GetTitle() {
        return name;
    }
}


public class StorageDict extends StorageItem {
    private StorageDict() { }

    static StorageDict instance = null;

    public static StorageDict GetInstance() {
        if (instance == null)
            instance = new StorageDict();
        return instance;
    }
    
    public String Name() {
        return "Dict";
    }

    public String Description() {
        return "Module storing dictionary entries";
    }

    protected void New(String[] args) throws Exception {
        if (args.length != 0)
            throw new Exception();
        StorageEntry entry = new DictContent();
        entry.New();
        TryUpdateEntries(null, entry);
        content.add(0, entry);
    }

    public void LoadFromArray(List<String[]> data) {
        for (String[] descr : data) {
            StorageEntry entry = new DictContent();
            entry.LoadFromArray(descr);
            content.add(0, entry);
        }
    }
}
