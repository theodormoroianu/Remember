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
    static public String RemoveDoubleSpaces(String s) {
        return s.trim().replaceAll("( +)", " ").trim();
    }

    /**
     * Checks if two strings are equal, up to case.
     * @return True if the strings are equal.
     */
    static public boolean CheckEqual(String a, String b) {
        a = a.trim();
        b = b.trim();
        return a.toLowerCase().equals(b.toLowerCase());
    }

    /**
     * Returns standard version of a title.
     */
    static public String Standard(String a) {
        return a.trim().toLowerCase();
    }

    /**
     * Joins a list of strings into a single string.
     * It adds spaces between the words.
     */
    static public String JoinWithSpaces(String[] arr) {
        return String.join(" ", arr);
    }
}
