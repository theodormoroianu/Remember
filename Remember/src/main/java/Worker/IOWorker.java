package Worker;
import java.util.Scanner;

public class IOWorker {
    
    private Scanner fin;
    
    private IOWorker() {
        fin = new Scanner(System.in);
    }

    private static IOWorker instance = null;

    public static IOWorker GetInstance() {
        if (instance == null)
            instance = new IOWorker();
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

    public void ClearConsole() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}
