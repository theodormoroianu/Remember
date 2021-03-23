package Worker;

import java.util.ArrayList;
import java.util.List;
import Storage.*;

public class ItemsWorker {
    private ItemsWorker() {
    }

    private static ItemsWorker instance = null;

    public static ItemsWorker GetInstance() {
        if (instance == null)
            instance = new ItemsWorker();
        return instance;
    }

    public List<StorageItem> GetStorageItems()
    {
        ArrayList<StorageItem> arr = new ArrayList<>();
        arr.add(StorageText.GetInstance());
        arr.add(StorageContact.GetInstance());
        arr.add(StorageURL.GetInstance());
        arr.add(StorageToDo.GetInstance());
        arr.add(StorageAttraction.GetInstance());
        arr.add(StorageMovie.GetInstance());
        arr.add(StorageMoney.GetInstance());
        arr.add(StorageDict.GetInstance());

        return arr;
    }
}
