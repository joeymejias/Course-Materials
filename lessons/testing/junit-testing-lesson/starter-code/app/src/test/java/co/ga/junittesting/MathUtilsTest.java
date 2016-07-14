package co.ga.junittesting;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by joey on 7/13/16.
 */
public class MathUtilsTest {

    @Test
    public void usefulMethods() throws Exception {
            assertEquals(4, 2 + 2);
            assertTrue(true);
            assertFalse(false);
            assertNull(null);
            assertNotNull("Not null");
    }

    @Test
    public void testIntMultiply() throws Exception {
        assertEquals(4, MathUtils.multiply(2,2));
    }

    @Test
    public void testDoubleMultiply() throws Exception {
        assertEquals(5, MathUtils.multiply(2.0,2.5),0);
    }

    @Test
    public void testIntSquare() throws Exception {
        assertEquals(4, MathUtils.square(2));
    }

    @Test
    public void testDoubleSquare() throws Exception {
        assertEquals(6.25, MathUtils.square(2.5),0);
    }

    @Test
    public void testIntAdd() throws Exception {
        assertEquals(4, MathUtils.add(2,2));
    }

    @Test
    public void testDoubleAdd() throws Exception {
        assertEquals(5, MathUtils.add(2.5,2.5),0);
    }

    @Test
    public void testIntPythagorean() throws Exception {
        assertEquals(8, MathUtils.pythagorean(2,2));
    }

    @Test
    public void testDoublePythagorean() throws Exception {
        assertEquals(12.5, MathUtils.pythagorean(2.5,2.5),0);
    }

}
