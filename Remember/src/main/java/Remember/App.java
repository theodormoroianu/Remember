package Remember;

import Interactive.Interactor;
import Storage.*;

import java.util.ArrayList;

/**
 * Entry point of the Remember app.
 */
public class App 
{
    static ArrayList<StorageItem> GetStorageItems()
    {
        ArrayList<StorageItem> arr = new ArrayList<>();
        arr.add(StorageText.GetInstance());
        arr.add(StorageContact.GetInstance());
        arr.add(StorageURL.GetInstance());
        arr.add(StorageToDo.GetInstance());
        arr.add(StorageAttraction.GetInstance());
        arr.add(StorageMovie.GetInstance());
        arr.add(StorageMoney.GetInstance());
        
        return arr;
    }
    public static void main(String[] args)
    {
        Interactor interactor = Interactor.GetInstance();
        
        if (args.length > 0)
            interactor.Execute(GetStorageItems(), args);
        else
            interactor.Interact(GetStorageItems());
    }
}
