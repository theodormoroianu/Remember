package Storage;

import java.util.List;

class TextContent extends StorageEntry {
    private String title;
    private String content;

    public TextContent(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public TextContent() { }

    public StorageEntry Copy() {
        return new TextContent(title, content);
    }
    
    public void LoadFromArray(String[] data) {
        title = data[0];
        content = data[1];
    }

    public String[] WriteToArray() {
        return new String[] { title, content };
    }

    public void Edit() throws Exception {
        title = UpdateEntry(title, "Title");
        content = UpdateEntry(content, "Content");
    }

    public void New() throws Exception {
        title = UpdateEntry("", "Title");
        content = UpdateEntry("", "Content");
    }

    public void Show() {
        System.out.println("Title: " + title);
        System.out.println("Content: " + content);
    }

    public String GetTitle() {
        return title;
    }
}

public class StorageText extends StorageItem {
    // Singleton

    private static StorageText instance = null;

    private StorageText() { }

    public static StorageText GetInstance() {
        if (instance == null)
            instance = new StorageText();
        return instance;
    }

    protected StorageEntry New(String[] args) throws Exception {
        if (args.length != 0)
            throw new Exception();
        StorageEntry entry = new TextContent();
        entry.New();
        TryUpdateEntries(null, entry);
        content.add(0, entry);
        return entry;
    }

    public String Name() {
        return "Text";
    }

    public String Description() {
        return "Module storing plain-text information.";
    }
    
    public void LoadFromArray(List<String[]> data) {
        for (String[] descr : data) {
            StorageEntry entry = new TextContent();
            entry.LoadFromArray(descr);
            content.add(0, entry);
        }
    }
}
