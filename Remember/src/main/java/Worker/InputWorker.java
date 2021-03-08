package Worker;
import java.util.Scanner;

public class InputWorker {
    
    private Scanner fin;
    
    private InputWorker() {
        fin = new Scanner(System.in);
    }

    private static InputWorker instance = null;

    public static InputWorker GetInstance() {
        if (instance == null)
            instance = new InputWorker();
        return instance;
    }

    public int GetInt() {
        return fin.nextInt();
    }

    public String GetLine() {
        return fin.nextLine();
    }

    public String[] GetWords() {
        return fin.nextLine().split(" ");
    }
}
