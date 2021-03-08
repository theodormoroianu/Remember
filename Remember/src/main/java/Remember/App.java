package Remember;

import Interactive.Interactor;
import Storage.StorageItem;
import Storage.StorageText;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Interactor interactor = Interactor.GetInstance();
        ArrayList<StorageItem> arr = new ArrayList<>();
        arr.add(new StorageText());
        interactor.Execute(arr);
    }
}
