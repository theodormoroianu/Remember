package Storage;

import java.util.List;

class AttractionContent extends StorageEntry {
    private String name;
    private String description;
    private String price;

    public AttractionContent() { }

    public AttractionContent(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public StorageEntry Copy() {
        return new AttractionContent(name, description, price);
    }
    
    public void LoadFromArray(String[] data) {
        name = data[0];
        description = data[1];
        price = data[2];
    }

    public String[] WriteToArray() {
        return new String[] { name, description, price };
    }

    public void New() throws Exception {
        name = UpdateEntry("", "Name");
        description = UpdateEntry("", "Description");
        price = UpdateEntry("Free", "Price");
    }

    public void Edit() throws Exception {
        name = UpdateEntry(name, "Name");
        description = UpdateEntry(description, "Description");
        price = UpdateEntry(price, "Price");
    }

    public void Show() {
        System.out.println("Name:          " + name);
        System.out.println("Description:   " + description);
        System.out.println("Price:         " + price);
    }

    @Override
    public String GetPrintableTitle() {
        return "[" + price + "] " + name;
    }

    public String GetTitle() {
        return name;
    }
}


public class StorageAttraction extends StorageItem {
    private StorageAttraction() { }

    static StorageAttraction instance = null;

    public static StorageAttraction GetInstance() {
        if (instance == null)
            instance = new StorageAttraction();
        return instance;
    }
    
    public String Name() {
        return "Attraction";
    }

    public String Description() {
        return "Module storing places to visit";
    }

    protected StorageEntry New(String[] args) throws Exception {
        if (args.length != 0)
            throw new Exception();
        StorageEntry entry = new AttractionContent();
        entry.New();
        TryUpdateEntries(null, entry);
        content.add(0, entry);
        return entry;
    }
    
    public void LoadFromArray(List<String[]> data) {
        for (String[] descr : data) {
            StorageEntry entry = new AttractionContent();
            entry.LoadFromArray(descr);
            content.add(0, entry);
        }
    }
}
