package Storage;

class URLContent extends StorageEntry {
    private String name;
    private String url;

    public URLContent() { }
    
    public URLContent(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public StorageEntry Copy() {
        return new URLContent(name, url);
    }

    public void New() throws Exception {
        name = UpdateEntry("", "Name");
        url = UpdateEntry("", "URL");
    }

    public void Edit() throws Exception {
        name = UpdateEntry(name, "Name");
        url = UpdateEntry(url, "Address");
    }

    public void Show() {
        System.out.println("Name:   " + name);
        System.out.println("URL:    " + url);
    }


    public String GetTile() {
        return name;
    }
}


public class StorageURL extends StorageItem {
    private StorageURL() { }

    static StorageURL instance = null;

    public static StorageURL GetInstance() {
        if (instance == null)
            instance = new StorageURL();
        return instance;
    }
    
    public String Name() {
        return "URL";
    }

    public String Description() {
        return "Module storing URLs";
    }

    protected void New(String[] args) throws Exception {
        if (args.length != 0)
            throw new Exception();
        StorageEntry entry = new URLContent();
        entry.New();
        TryUpdateEntries(null, entry);
        content.add(0, entry);
    }
}
