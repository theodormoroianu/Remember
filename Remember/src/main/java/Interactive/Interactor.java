package Interactive;

import java.io.PrintStream;
import java.util.ArrayList;
import Storage.StorageItem;
import Worker.InputWorker;
import Worker.StringWorker;
import java.util.Arrays;

public class Interactor {
    
    // Copy of System.out
    PrintStream out;

    // storages passed to the interactor.
    ArrayList <StorageItem> storages;

    private Interactor() {
        out = System.out;
    }

    private static Interactor instance = null;

    /**
     * Returns the interactor singleton object.
     */
    public static Interactor GetInstance() {
        if (instance == null)
            instance = new Interactor();
        return instance;
    }

    private void DisplayHelp() {
        out.println("Usage: Command [ARGS | MODULE]... [OBJ]...");
        out.println("Rɘmɘmbɘr -- Simple CLI tool for storing information.");
        out.println("\nAvailable commands:");
        out.println("    * Exit               Exists Rɘmɘmbɘr.");
        out.println("    * Help               Displays this message.");
        out.println("\nAvailable modules:");
        for (StorageItem comp : storages)
            out.println(("    * " + comp.Name() + "                          ").substring(0, 25) + comp.Description());
        out.println("For more help on a module, please use: [MODULE] help");
    }

    /**
     * Parses the arguments.
     * @return true if the interactor should close.
     */
    public boolean Parse(String[] args) {
        if (args.length == 0)
            return false;

        StringWorker stringworker = StringWorker.GetInstance();

        args[0] = args[0].toLowerCase();

        switch(args[0]) {
            case "exit":
                return true;
            case "help":
                DisplayHelp();
                return false;
        }

        for (StorageItem storage : storages) {
            if (stringworker.CheckEqual(storage.Name(), args[0])) {
                storage.Execute(Arrays.copyOfRange(args, 1, args.length));
                return false;
            }
        }

        out.println("ERROR: Command was not understood.\nPlease use \"Help\" for a list of available commands.");
        return false;
    }

    /**
     * Starts an interactive CLI interface.
     */
    public void Execute(ArrayList <StorageItem> storages) {
        this.storages = storages;

        out.println("Rɘmɘmbɘr 0.0.1 (8 March 2021)");
        out.println("Type \"credits\" or \"help\" for more info.");

        InputWorker input = InputWorker.GetInstance();
        
        while (true) {
            out.print(" $ ");
            String[] result = input.GetWords();
            if (Parse(result))
                break;
        }

        out.println("Leaving interactor ...");
    }
}