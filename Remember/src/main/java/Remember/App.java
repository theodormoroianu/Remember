package Remember;

import Interactive.Interactor;
import Worker.*;

/**
 * Entry point of the Remember app.
 */
public class App 
{
    public static void main(String[] args)
    {
        PersistenceWorker.GetInstance().LoadFromMemory();

        Interactor interactor = Interactor.GetInstance();
        
        if (args.length > 0)
            interactor.Execute(ItemsWorker.GetInstance().GetStorageItems(), args);
        else
            interactor.Interact(ItemsWorker.GetInstance().GetStorageItems());

        PersistenceWorker.GetInstance().SaveToMemory();
    }
}
