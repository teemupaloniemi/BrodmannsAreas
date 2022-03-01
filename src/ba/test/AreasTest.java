package ba.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import ba.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.03.01 23:24:35 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class AreasTest {



  // Generated by ComTest BEGIN
  /** 
   * testAdd32 
   * @throws TilaException when error
   */
  @Test
  public void testAdd32() throws TilaException {    // Areas: 32
    Areas areas = new Areas(); 
    Area a1 = new Area(), a2 = new Area(); 
    assertEquals("From: Areas line: 36", 0, areas.getSize()); 
    areas.add(a1); assertEquals("From: Areas line: 37", 1, areas.getSize()); 
    areas.add(a2); assertEquals("From: Areas line: 38", 2, areas.getSize()); 
    areas.add(a1); assertEquals("From: Areas line: 39", 3, areas.getSize()); 
    assertEquals("From: Areas line: 40", a1, areas.get(0)); 
    assertEquals("From: Areas line: 41", a2, areas.get(1)); 
    assertEquals("From: Areas line: 42", a1, areas.get(2)); 
    assertEquals("From: Areas line: 43", false, areas.get(1) == a1); 
    assertEquals("From: Areas line: 44", true, areas.get(1) == a2); 
    try {
    assertEquals("From: Areas line: 45", a1, areas.get(3)); 
    fail("Areas: 45 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    areas.add(a1); assertEquals("From: Areas line: 46", 4, areas.getSize()); 
    areas.add(a1); assertEquals("From: Areas line: 47", 5, areas.getSize()); 
    try {
    areas.add(a1); 
    fail("Areas: 48 Did not throw TilaException");
    } catch(TilaException _e_){ _e_.getMessage(); }
  } // Generated by ComTest END
}