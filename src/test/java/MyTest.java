import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class MyTest
{
    @Test
    void unitTest()
    {
        assertEquals(5,5, "Messages are equal");
    }

    @Test
    void unitTest2()
    {
        assertEquals(5.0, 5.01, 0.02);
    }

    @Test
    void unitTest3()
    {
        int[] a = {1,2,3};
        int[] b = {1,2,3};
        assertArrayEquals(a,b);
    }

    @Test
    void unitTest4()
    {
        assertTrue(5==5);
    }

    @Test
    void unitTest5()
    {
        assertFalse(5==4);
    }

    @Test
    void unitTest6()
    {
        assertNull(null);
    }

    @Test
    void unitTest7()
    {
    }
    @Test
    void unitTest8()
    {
        assertNotNull("Hello");
    }

    @Test
    void unitTest9()
    {
        assertThrows(NullPointerException.class, this::throwsException);
    }

    void throwsException()
    {
        throw new NullPointerException();
    }

    @Test
    void unitTest10() throws NullPointerException
    {
       throw new NullPointerException();
    }
}