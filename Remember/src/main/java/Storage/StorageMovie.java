package Storage;

import java.util.List;

class MovieContent extends StorageEntry {
    private String name;
    private String description;
    private int rating;

    public MovieContent() { }

    public MovieContent(String name, String description, int rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }

    public StorageEntry Copy() {
        return new MovieContent(name, description, rating);
    }
    
    public void LoadFromArray(String[] data) {
        name = data[0];
        description = data[1];
        rating = Integer.parseInt(data[2]);
    }

    public String[] WriteToArray() {
        return new String[] { name, description, String.valueOf(rating) };
    }

    public void New() throws Exception {
        name = UpdateEntry("", "Name");
        description = UpdateEntry("", "Description");
        String rating_str = UpdateEntry("0", "Rating (out of 5)");

        try {
            rating = Integer.parseInt(rating_str);
        } catch (Exception e) {
            throw new Exception("Rating given isn't a number!");
        }
        if (rating < 0 || rating > 5)
            throw new Exception("Rating given isn't valid!");
    }

    public void Edit() throws Exception {
        name = UpdateEntry(name, "Name");
        description = UpdateEntry(description, "Description");
        String rating_str = UpdateEntry("" + rating, "Rating (out of 5)");

        try {
            rating = Integer.parseInt(rating_str);
        } catch (Exception e) {
            throw new Exception("Rating given isn't a number!");
        }
        if (rating < 0 || rating > 5)
            throw new Exception("Rating given isn't valid!");
    }

    private String ComputeRating() {
        StringBuilder str = new StringBuilder("☆ ☆ ☆ ☆ ☆ ");
        for (int i = 0; i < rating; i++)
            str.setCharAt(2 * i, '★');
        return str.toString();
    }

    public void Show() {
        System.out.println("Name:          " + name);
        System.out.println("Description:   " + description);
        System.out.println("Rating:        " + ComputeRating());
    }

    @Override
    public String GetPrintableTitle() {
        return "[" + ComputeRating() + "] " + name;
    }

    public String GetTitle() {
        return name;
    }
}


public class StorageMovie extends StorageItem {
    private StorageMovie() { }

    static StorageMovie instance = null;

    public static StorageMovie GetInstance() {
        if (instance == null)
            instance = new StorageMovie();
        return instance;
    }
    
    public String Name() {
        return "Movie";
    }

    public String Description() {
        return "Module storing movies to watch";
    }

    protected StorageEntry New(String[] args) throws Exception {
        if (args.length != 0)
            throw new Exception();
        StorageEntry entry = new MovieContent();
        entry.New();
        TryUpdateEntries(null, entry);
        content.add(0, entry);
        return entry;
    }
    
    public void LoadFromArray(List<String[]> data) {
        for (String[] descr : data) {
            StorageEntry entry = new MovieContent();
            entry.LoadFromArray(descr);
            content.add(0, entry);
        }
    }
}
