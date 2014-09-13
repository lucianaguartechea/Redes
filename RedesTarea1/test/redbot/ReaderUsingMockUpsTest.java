package redbot;

import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author d
 */
public class ReaderUsingMockUpsTest {
    
    public ReaderUsingMockUpsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of get method, of class ReaderUsingMockUps.
     */
    @Test
    public void testGet() throws Exception {
        System.out.println("get");
        String url = "";
        ReaderUsingMockUps instance = new ReaderUsingMockUps();
        String expResult = "";
        String result = instance.get(url);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of post method, of class ReaderUsingMockUps.
     */
    @Test
    public void testPost() throws Exception {
        System.out.println("post");
        String url = "";
        ReaderUsingMockUps instance = new ReaderUsingMockUps();
        String expResult = "";
        String result = instance.post(url);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of head method, of class ReaderUsingMockUps.
     */
    @Test
    public void testHead() throws Exception {
        System.out.println("head");
        String url = "";
        ReaderUsingMockUps instance = new ReaderUsingMockUps();
        String expResult = "";
        String result = instance.head(url);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of headers method, of class ReaderUsingMockUps.
     */
    @Test
    public void testHeaders() {
        System.out.println("headers");
        ReaderUsingMockUps instance = new ReaderUsingMockUps();
        Map<String, String> expResult = null;
        Map<String, String> result = instance.headers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
