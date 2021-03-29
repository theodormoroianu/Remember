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
        AuditWorker.GetInstance().Log("Starting application");
        PersistenceWorker.GetInstance().LoadFromMemory();

        Interactor interactor = Interactor.GetInstance();
        
        if (args.length > 0) {
            AuditWorker.GetInstance().Log("Detected arguments. Executing arguments.");
            interactor.Execute(ItemsWorker.GetInstance().GetStorageItems(), args);
        }
        else {
            AuditWorker.GetInstance().Log("No arguments detected. Starting in interactive mode.");
            interactor.Interact(ItemsWorker.GetInstance().GetStorageItems());
        }
        PersistenceWorker.GetInstance().SaveToMemory();
    }
}
