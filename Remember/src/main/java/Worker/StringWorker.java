package Worker;

/**
 * Singleton class able to perform various operations on strings.
 */
public class StringWorker {

    private StringWorker() { }
    
    private static StringWorker instance = null;

    /**
     * Returns an instance of the singleton class.
     * @return instance of StringWorker.
     */
    public static StringWorker GetInstance() {
        if (instance == null)
            instance = new StringWorker();
        return instance;
    }

    /**
     * Removes double spaces from a string.
     * Also trims begin and end of the string.
     * @return trimmed string.
     */
    public String RemoveDoubleSpaces(String s) {
        return s.replaceAll("( +)", " ").trim();
    }

    /**
     * Checks if two strings are equal, up to case.
     * @return True if the strings are equal.
     */
    public boolean CheckEqual(String a, String b) {
        return a.toLowerCase().equals(b.toLowerCase());
    }
}
