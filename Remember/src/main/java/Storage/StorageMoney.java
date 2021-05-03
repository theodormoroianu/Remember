package Storage;

import java.util.List;

import Worker.PersistenceWorker;
import Worker.StringWorker;

class MoneyContent extends StorageEntry {
    private String name;
    private String description;
    private double sum;
    private String unit;

    public MoneyContent() { }

    public MoneyContent(String name, String description, double sum, String unit) {
        this.name = name;
        this.description = description;
        this.sum = sum;
        this.unit = unit;
    }   

    public void LoadFromArray(String[] data) {
        name = data[0];
        description = data[1];
        sum = Double.parseDouble(data[2]);
        unit = data[3];
    }

    public String[] WriteToArray() {
        return new String[] { name, description, String.valueOf(sum), unit };
    }

    public StorageEntry Copy() {
        return new MoneyContent(name, description, sum, unit);
    }

    public void New() throws Exception {
        name = UpdateEntry("", "Name");
        description = UpdateEntry("", "Description");
        unit = UpdateEntry("$", "Currency");
        
        String initial_sum = UpdateEntry("0", "Quantity");

        try {
            sum = Double.parseDouble(initial_sum);
        } catch (Exception e) {
            throw new Exception("quantity given isn't a number!");
        }
    }

    public void Edit() throws Exception {
        name = UpdateEntry(name, "Name");
        description = UpdateEntry(description, "Description");
        unit = UpdateEntry(unit, "Currency");
        
        String initial_sum = UpdateEntry("" + sum, "Quantity");

        try {
            sum = Double.parseDouble(initial_sum);
        } catch (Exception e) {
            throw new Exception("quantity given isn't a number!");
        }
        if (sum < 0)
            throw new Exception("quantity given isn't valid!");
    }

    public void Pay() throws Exception {
        String diff = UpdateEntry("+0", "Balance Update");
        diff = diff.replaceAll(" ", "");

        try {
            MoneyContent old_me = (MoneyContent)Copy();
            double delta = Double.parseDouble(diff);
            sum += delta;
            PersistenceWorker
                .GetInstance()
                .UpdateEntry(StorageMoney.GetInstance(), old_me, this);
        }
        catch (Exception e) {
            throw new Exception("update given isn't a number");
        }
    }

    public void Show() {
        System.out.println("Name:          " + name);
        System.out.println("Description:   " + description);
        System.out.println("Quantity:      " + sum);
    }

    @Override
    public String GetPrintableTitle() {
        return "[" + sum + unit + "] " + name;
    }

    public String GetTitle() {
        return name;
    }
}


public class StorageMoney extends StorageItem {
    private StorageMoney() {
        additional_commands.add("Pay     <NAME>");
    }

    static StorageMoney instance = null;

    public static StorageMoney GetInstance() {
        if (instance == null)
            instance = new StorageMoney();
        return instance;
    }
    
    public String Name() {
        return "Money";
    }

    public String Description() {
        return "Module storing sums of money";
    }

    protected StorageEntry New(String[] args) throws Exception {
        if (args.length != 0)
            throw new Exception();
        StorageEntry entry = new MoneyContent();
        entry.New();
        TryUpdateEntries(null, entry);
        content.add(0, entry);
        return entry;
    }

    private void Pay(String[] args) throws Exception {
        MoneyContent entry = (MoneyContent) FindEntry(args);

        if (entry == null)
            out.println("No entry with the given name was found!");
        else {
            entry.Pay();
        }
    }

    @Override
    protected void CustomCommand(String s, String[] args) throws Exception {
        if (StringWorker.CheckEqual(s, "pay"))
            Pay(args);
        else
            throw new Exception();
    }

    public void LoadFromArray(List<String[]> data) {
        for (String[] descr : data) {
            StorageEntry entry = new MoneyContent();
            entry.LoadFromArray(descr);
            content.add(0, entry);
        }
    }
}
