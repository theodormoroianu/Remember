package Storage;

import java.util.ArrayList;

import Worker.StringWorker;

class TextContent {
    private String title;
    private String content;

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

public class StorageText implements StorageItem {
    
    private ArrayList <TextContent> content;
    
    public String Name() {
        return "Text";
    }

    public String Description() {
        return "Module storing plain-text information.";
    }

    public void Load(String path) {
        throw new Error("Feature not implemented!");
    }

    public void Unload(String path) {
        throw new Error("Feature not implemented!");
    }

    public void Execute(String[] args) {
        StringWorker stringworker = StringWorker.GetInstance();

        try {
            // If args == {} then exit.
            if (args.length == 0)
                throw new Exception();
            if (args.length == 1) {
                String s = args[0];
                if (!stringworker.CheckEqual(args[0], "list"))
                    throw new Exception();
                System.out.println("Saved notes:\n");
                for (int i = 0; i < content.size(); i++)
                    System.out.println((i + 1) + ": \"" + content.get(i).GetTile() + "\"");
            }
            
        } catch(Exception e) {
            System.out.println("Usage: " + Name() + " [View / Edit / Delete] <Title>\n    List");
        }
    }
}
