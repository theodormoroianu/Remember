package Storage;

import java.util.ArrayList;

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

    public void Execute(ArrayList <String> args) {
        try {
            // If args == {} then exit.
            if (args.size() == 0)
                throw new Exception();
            if (args.size() == 1) {
                String s = args.get(0);
                if (!s.equals("list"))
                    throw new Exception();
                System.out.println("Saved notes:\n");
                for (int i = 0; i < content.size(); i++)
                    System.out.println((i + 1) + ": \"" + content.get(i).GetTile() + "\"");
            }
            
        } catch(Exception e) {
            System.out.println("Usage: [View / Edit / Delete] <Title>\n    List");
        }
    }
}
