package Remember;

import Interactive.Interactor;
import Storage.StorageItem;
import Storage.StorageText;

import java.util.ArrayList;

/**
 * Entry point of the Remember app.
 */
public class App 
{
    public static void main( String[] args )
    {
        Interactor interactor = Interactor.GetInstance();
        ArrayList<StorageItem> arr = new ArrayList<>();
        arr.add(StorageText.GetInstance());
        interactor.Execute(arr);
    }
}
