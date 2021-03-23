package Interactive;

import java.io.PrintStream;
import Storage.StorageItem;
import Worker.IOWorker;
import Worker.StringWorker;
import java.util.Arrays;
import java.util.List;

public class Interactor {
    
    // Copy of System.out
    PrintStream out;

    // storages passed to the interactor.
    List <StorageItem> storages;

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
        out.println("    * Clear              Clears the console.");
        out.println("\nAvailable modules:");
        for (StorageItem comp : storages)
            out.println(("    * " + comp.Name() + "                          ").substring(0, 25) + comp.Description());
        out.println("For more help on a module, please use: [MODULE] help");
    }

    private void DisplayCredits() {
        out.println("Rɘmɘmbɘr -- Simple CLI tool for storing information.");
        out.println("Author:     Theodor Moroianu");
        out.println("Project:    Remember");
        out.println("Course:     Advanced OOP");
    }
    /**
     * Parses the arguments.
     * @return true if the interactor should close.
     */
    public boolean Parse(String[] args) {
        if (args.length == 0)
            return false;

        args[0] = args[0].toLowerCase();

        switch(args[0]) {
            case "exit":
                return true;
            case "help":
                DisplayHelp();
                return false;
            case "clear":
                IOWorker.GetInstance().ClearConsole();
                return false;
            case "credits":
                DisplayCredits();
                return false;
        }

        for (StorageItem storage : storages) {
            if (StringWorker.CheckEqual(storage.Name(), args[0])) {
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
    public void Interact(List <StorageItem> storages) {
        this.storages = storages;

        out.println("Rɘmɘmbɘr 0.0.1 (8 March 2021)");
        out.println("Type \"credits\" or \"help\" for more info.");

        IOWorker input = IOWorker.GetInstance();
        
        while (true) {
            out.print(" $ ");
            String[] result = input.GetWords();
            if (Parse(result))
                break;
        }

        out.println("Leaving interactor ...");
    }

    /**
     * Execute one command.
     */
    public void Execute(List <StorageItem> storages, String[] args) {
        this.storages = storages;
        
        Parse(args);
    }
}
