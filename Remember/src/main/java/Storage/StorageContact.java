package Storage;

import java.util.List;

class ContactContent extends StorageEntry {
    private String name;
    private String address, phone_nummber, email;

    public ContactContent() { }

    public ContactContent(
            String name, 
            String address,
            String phone_number,
            String email) {
        this.name = name;
        this.address = address;
        this.phone_nummber = phone_number;
        this.email = email;
    }

    public StorageEntry Copy() {
        return new ContactContent(name, address, phone_nummber, email);
    }
        
    public void LoadFromArray(String[] data) {
        name = data[0];
        address = data[1];
        phone_nummber = data[2];
        email = data[3];
    }

    public String[] WriteToArray() {
        return new String[] { name, address, phone_nummber, email };
    }

    public void New() throws Exception {
        name = UpdateEntry("", "Name");
        address = UpdateEntry("Unknown", "Address");
        phone_nummber = UpdateEntry("Unknown", "Phone number");
        email = UpdateEntry("Unknown", "Email");
    }

    public void Edit() throws Exception {
        name = UpdateEntry(name, "Name");
        address = UpdateEntry(address, "Address");
        phone_nummber = UpdateEntry(phone_nummber, "Phone number");
        email = UpdateEntry(email, "Email");
    }

    public void Show() {
        System.out.println("Name:         " + name);
        System.out.println("Address:      " + address);
        System.out.println("Phone Number: " + phone_nummber);
        System.out.println("Email:        " + email);
    }

    public String GetTitle() {
        return name;
    }
}


public class StorageContact extends StorageItem {
    private StorageContact() { }

    static StorageContact instance = null;

    public static StorageContact GetInstance() {
        if (instance == null)
            instance = new StorageContact();
        return instance;
    }
    
    public String Name() {
        return "Contact";
    }

    public String Description() {
        return "Module storing contacts";
    }

    protected StorageEntry New(String[] args) throws Exception {
        if (args.length != 0)
            throw new Exception();
        StorageEntry entry = new ContactContent();
        entry.New();
        TryUpdateEntries(null, entry);
        content.add(0, entry);
        return entry;
    }

    public void LoadFromArray(List<String[]> data) {
        for (String[] descr : data) {
            StorageEntry entry = new ContactContent();
            entry.LoadFromArray(descr);
            content.add(0, entry);
        }
    }
}
