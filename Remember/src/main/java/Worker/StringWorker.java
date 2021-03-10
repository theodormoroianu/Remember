package Worker;

import java.util.regex.Pattern;

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
        return s.replaceAll("( +)", " ").trim();
    }

    /**
     * Checks if two strings are equal, up to case.
     * @return True if the strings are equal.
     */
    static public boolean CheckEqual(String a, String b) {
        return a.toLowerCase().equals(b.toLowerCase());
    }

    /**
     * Checks if a string matches a given regex.
     * If regex is empty then always match.
     * @return Returns if the string matched the regex.
     */
    static public boolean MatchRegex(String str, String regex) {
        if (str.equals(""))
            str = ".*";
        return Pattern.matches(regex, str);
    }

    /**
     * Joins a list of strings into a single string.
     * It adds spaces between the words.
     */
    static public String JoinWithSpaces(String[] arr) {
        return String.join(" ", arr);
    }
}
