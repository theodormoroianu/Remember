package Worker;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 * Testing the StringWorker class.
 */
public class StringWorkerTest {
    
    @Test
    public void EqualityWorks()
    {
        assertTrue(StringWorker.CheckEqual("", ""));
        assertTrue(StringWorker.CheckEqual("abCD", "ABcd"));
        assertTrue(StringWorker.CheckEqual(" a b C D    ", "A B c d"));
        assertFalse(StringWorker.CheckEqual(" aw b C D    ", "A B c d"));
        assertFalse(StringWorker.CheckEqual("a b", "ab"));
    }

    @Test
    public void JoinWithSpacesWorks()
    {
        assertEquals(StringWorker.JoinWithSpaces(new String[]{"Buna", "Ziua"}), "Buna Ziua");
        assertEquals(StringWorker.JoinWithSpaces(new String[]{"Buna"}), "Buna");
        assertEquals(StringWorker.JoinWithSpaces(new String[]{"Buna", "Ziua", "Domnu", "ABC"}), "Buna Ziua Domnu ABC");
    }

    @Test
    public void RemoveDoubleSpacesWorks() {
        assertEquals(StringWorker.RemoveDoubleSpaces("  Buna    Ziua   Copii  "), "Buna Ziua Copii");
        assertEquals(StringWorker.RemoveDoubleSpaces("    "), "");
        assertEquals(StringWorker.RemoveDoubleSpaces("a b c d  e  f "), "a b c d e f");
    }
}
