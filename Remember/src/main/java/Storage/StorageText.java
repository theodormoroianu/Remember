package Storage;

import java.util.ArrayList;

class TextContent extends StorageEntry {
    private String title;
    private String content;

    public TextContent() { }

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


    TextContent(String title, String content) {
        this.content = content;
        this.title = title;
    }

    public String GetTile() {
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

    protected void New(String[] args) throws Exception {
        if (args.length != 0)
            throw new Exception();
        StorageEntry entry = new TextContent();
        entry.New();
        content.add(entry);
    }

    public String Name() {
        return "Text";
    }

    public String Description() {
        return "Module storing plain-text information.";
    }
}
