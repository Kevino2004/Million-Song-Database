import java.util.NoSuchElementException;
import student.TestCase;
/**
 * Double Linked List test class.
 * 
 * @author Kevin O'Neill <ckoneill04> Kavian Rahiab <kavianr22>
 * @version 9/13/24
 *
 */
public class DoubleLLTest extends TestCase
{    /**
     * the list we will use
     */
    private DoubleLL<String> list;

    /**
     * run before every test case
     */
    @Override
    public void setUp() {
        list = new DoubleLL<String>();
    }

    /**
     * Tests that an IndexOutOfBounds exception is thrown when the index is
     * greater than or equal to size and less than zero
     */
    public void testRemoveException() {
        list.add("A");
        Exception e = null;
        try {
            list.remove(2);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
        e = null;
        try {
            list.remove(-1);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
    }

    /**
     * Tests that objects can be removed at the beginning and end and that the
     * size is changed
     */
    public void testRemoveIndex() {
        list.add("A");
        list.add("B");
        assertEquals(2, list.size());

        assertTrue(list.remove(1));
        assertEquals(1, list.size());

        list.add("B");
        assertTrue(list.remove(0));
        assertEquals(1, list.size());
    }

    /**
     * Tests the add method. Ensures that it adds the object is added at the end
     * and the size is increased
     */
    public void testAdd() {
        assertEquals(0, list.size());
        list.add("A");
        assertEquals(1, list.size());
        list.add("B");
        assertEquals(2, list.size());
        assertEquals("B", list.get(1));

    }

    /**
     * Tests that objects can be added at the beginning and end and that they
     * are placed correctly
     */
    public void testAddIndex() {
        list.add("B");
        list.add(0, "A");
        assertEquals("A", list.get(0));
        assertEquals(2, list.size());

        list.add(2, "D");
        assertEquals("D", list.get(2));
        assertEquals(3, list.size());

        list.add(2, "C");
        assertEquals("C", list.get(2));
        assertEquals(4, list.size());

        try {
            list.add(-1, "X");
            fail("Expected IndexOutOfBoundsException for index -1.");
        } 
        catch (IndexOutOfBoundsException e) {
            // Exception is expected
        }

        try {
            list.add(5, "Y");
            fail("Expected IndexOutOfBoundsException for index 5.");
        } 
        catch (IndexOutOfBoundsException e) {
            // Exception is expected
        }
    }

    /**
     * This tests that the add method throws a null pointer exception when
     * adding null data to the list
     */
    public void testAddNullException() {
        Exception e = null;
        try {
            list.add(null);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
    }

    /**
     * This tests that the add method throws a Invalid argument when adding null
     * data to the list
     */
    public void testAddIndexNullException() {
        Exception e = null;
        try {
            list.add(0, null);
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IllegalArgumentException);
    }

    /**
     * This tests when the add method is called and the index is greater than
     * size or less than zero
     */
    public void testAddException() {
        list.add("A");
        Exception e = null;
        try {
            list.add(2, "B");
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);
        e = null;
        try {
            list.add(-1, "B");
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue( e instanceof IndexOutOfBoundsException);
    }

    /**
     * Tests removing a object changes the size appropiately and that you can
     * remove the first and last elements
     */
    public void testRemoveObj() {
        assertFalse(list.remove(null)); 

        list.add("A");
        list.add("B");
        assertEquals(2, list.size());

        assertTrue(list.remove("A"));
        assertEquals("B", list.get(0));
        assertEquals(1, list.size());

        list.add("C");
        assertTrue(list.remove("C"));
        assertEquals("B", list.get(0));
        assertEquals(1, list.size());

        assertFalse(list.remove("X"));
        assertEquals(1, list.size());
    }

    /**
     * Tests get when the index is greater than or equal to size and when the
     * index is less than zero
     */
    public void testGetException() {
        Exception exception = null;
        try {
            list.get(-1);
        } 
        catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof IndexOutOfBoundsException);
        exception = null;
        list.add("A");
        try {
            list.get(1);
        } 
        catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertTrue(exception instanceof IndexOutOfBoundsException);
    }

    /**
     * Test contains when it does and does not contain the object
     */
    public void testContains() {
        assertFalse( list.contains("A"));
        list.add("A");
        assertTrue(list.contains("A"));
        assertFalse(list.contains("B"));
        list.add("B");
        assertTrue(list.contains("B"));
    }

    /**
     * Test lastIndexOf when the list is empty, when the object is not in the
     * list, and when it is at the beginning or end
     */
    public void testLastIndexOf() {
        assertEquals( -1, list.lastIndexOf("A"));
        list.add("A");
        assertEquals(0, list.lastIndexOf("A"));
        list.add("A");
        assertEquals(1, list.lastIndexOf("A"));
        list.add("B");
        assertEquals(1, list.lastIndexOf("A"));
        assertEquals(2, list.lastIndexOf("B"));
        list.add("A");
        assertEquals(3, list.lastIndexOf("A"));
    }

    /**
     * Tests isEmpty when empty and full
     */
    public void testIsEmpty() {
        assertTrue( list.isEmpty());
        list.add("A");
        assertFalse(list.isEmpty());
    }

    /**
     * Ensures that all of the objects are cleared and the size is changed
     */
    public void testClear() {
        list.add("A");
        list.clear();
        assertEquals(0, list.size());
        assertFalse(
                list.contains("A"));
    }

    /**
     * Tests the toString when there are 0, 1, and 2 objects in the list
     */
    public void testToString() {
        // Empty list check
        assertEquals("{}", list.toString());

        // Add single element
        list.add("A");
        assertEquals("{A}", list.toString());

        // Add second element
        list.add("B");
        assertEquals("{A, B}", list.toString());

        // Clear the list and check empty again
        list.remove(0);
        list.remove(0);
        assertEquals("{}", list.toString());
    }
    
    /**
     * tests toString when it is empty
     */
    public void testToStringEmpty()
    {
        String expected = "{}";
        String actual = list.toString();
        assertEquals(expected, actual);
    }

    /**
     * Tests removing from an empty list
     */
    public void testRemoveFromEmpty() {
        list.add("dance");
        list.add(0, "safety");
        list.clear();
        assertFalse(list.remove("safety"));
        Exception exception;
        exception = null;
        try {
            list.remove(0);
        } 
        catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertTrue(  exception instanceof IndexOutOfBoundsException);

        DoubleLL<String> emptyList = new DoubleLL<String>();
        exception = null;
        try {
            emptyList.remove(0);
        } 
        catch (IndexOutOfBoundsException e) {
            exception = e;
        }
        assertTrue( exception instanceof IndexOutOfBoundsException);
    }
    
    // ----------------------------------------------------------
    /**
     * tests add out of bounds
     */
    public void testAddException2() {
        // Test valid case
        list.add(0, "A");  // This should work if the list is initially empty

        Exception e = null;
        try {
            list.add(2, "B");  // index 2 is out of bounds if size is 1
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);

        e = null;
        try {
            list.add(-1, "B");  // negative index should throw exception
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof IndexOutOfBoundsException);

        e = null;
        try {
            list.add(1, "B");  // index 1 is valid if size is 1
            // Test adding at the end
            list.add(1, "C");  
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertNull(e);

        // Additional case to check adding to an empty list
        e = null;
        try {
            list.add(0, "D");  // index 0 should work if the list was empty
        } 
        catch (Exception exception) {
            e = exception;
        }
        assertNull(e);
    }
    
}
