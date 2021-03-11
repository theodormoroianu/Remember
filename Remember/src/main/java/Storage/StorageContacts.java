package Storage;

class ContactContent extends StorageEntry {
    private String name;
    private String address, phone_nummber, email;

    public ContactContent() { }

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


    public String GetTile() {
        return name;
    }
}


public class StorageContacts extends StorageItem {
    private StorageContacts() { }

    static StorageContacts instance = null;

    public static StorageContacts GetInstance() {
        if (instance == null)
            instance = new StorageContacts();
        return instance;
    }
    
    public String Name() {
        return "Contacts";
    }

    public String Description() {
        return "Module storing contacts";
    }

    protected void New(String[] args) throws Exception {
        if (args.length != 0)
            throw new Exception();
        StorageEntry entry = new ContactContent();
        entry.New();
        content.add(entry);
    }
}
