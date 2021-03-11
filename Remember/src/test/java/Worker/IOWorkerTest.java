package Worker;

import static org.junit.Assert.*;

import org.junit.Test;
import java.io.*;

/**
 * Testing the StringWorker class.
 */
public class IOWorkerTest {
    
    @Test
    public void ReadIntWorks()
    {
        IOWorker.DeleteInstance();

        System.setIn(new ByteArrayInputStream("123 345 -12 0 3".getBytes()));
        
        assertEquals(IOWorker.GetInstance().GetInt(), 123);
        assertEquals(IOWorker.GetInstance().GetInt(), 345);
        assertEquals(IOWorker.GetInstance().GetInt(), -12);
        assertEquals(IOWorker.GetInstance().GetInt(), 0);
        assertEquals(IOWorker.GetInstance().GetInt(), 3);
    }

    @Test
    public void GetLineWorks()
    {
        IOWorker.DeleteInstance();

        System.setIn(new ByteArrayInputStream("Hi,\nThanks for looking at me ;)\nHow are you??\n".getBytes()));
        
        assertEquals(IOWorker.GetInstance().GetLine(), "Hi,");
        assertEquals(IOWorker.GetInstance().GetLine(), "Thanks for looking at me ;)");
        assertEquals(IOWorker.GetInstance().GetLine(), "How are you??");
    }

    @Test
    public void GetWordsWorks() {
        IOWorker.DeleteInstance();

        System.setIn(new ByteArrayInputStream("Hi,\nThanks for looking at me ;)\nHow are you??\n".getBytes()));
        
        assertArrayEquals(IOWorker.GetInstance().GetWords(), new String[]{ "Hi," });
        assertArrayEquals(IOWorker.GetInstance().GetWords(), new String[]{ "Thanks", "for", "looking", "at", "me", ";)" });
        assertArrayEquals(IOWorker.GetInstance().GetWords(), new String[]{ "How", "are", "you??" });
    }

    @Test
    public void AllWorks() {
        IOWorker.DeleteInstance();

        System.setIn(new ByteArrayInputStream("Hi,\nThanks for looking at me ;)\nHow are you??\n".getBytes()));
        
        assertArrayEquals(IOWorker.GetInstance().GetWords(), new String[]{ "Hi," });
        assertArrayEquals(IOWorker.GetInstance().GetWords(), new String[]{ "Thanks", "for", "looking", "at", "me", ";)" });
        assertArrayEquals(IOWorker.GetInstance().GetWords(), new String[]{ "How", "are", "you??" });
    }
}
