package Storage;

import java.util.ArrayList;

import Worker.IOWorker;

class TextContent implements StorageEntry {
    private String title;
    private String content;

    public TextContent() { }

    public void Edit() {
        System.out.println("Former title: " + title);
        System.out.println("New title (leave blank to not modify): ");
        String new_title = IOWorker.GetInstance().GetLine();
        if (!new_title.isEmpty())
            title = new_title; // TODO: not be able to have same title twice
        System.out.println("Former content: " + content);
        System.out.println("New content (leave blank to not modify): ");
        String new_content = IOWorker.GetInstance().GetLine();
        if (!new_content.isEmpty())
            content = new_content;
    }

    public void New() {
        System.out.print("Title: ");
        title = IOWorker.GetInstance().GetLine();
        System.out.print("Content: ");
        content = IOWorker.GetInstance().GetLine();
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

    public void SetTitle(String title) {
        this.title = title;
    }

    public String GetContent() {
        return content;
    }

    public void SetContent(String content) {
        this.content = content;
    }
}

public class StorageText extends StorageItem {
    // Singleton

    private static StorageText instance = null;

    private StorageText() {
        content = new ArrayList <> ();
        out = System.out;
    }

    public static StorageText GetInstance() {
        if (instance == null)
            instance = new StorageText();
        return instance;
    }

    protected void New(String[] args) {
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
