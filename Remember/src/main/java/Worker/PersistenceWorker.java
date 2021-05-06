package Worker;

public class PersistenceWorker {
    
    private PersistenceWorker() {
    }

    public static StorageWorker GetInstance() {
        return CSVWorker.GetInstance();
    }
}
