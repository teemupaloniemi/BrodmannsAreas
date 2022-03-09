package ba.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import ba.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.03.09 12:04:03 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class LocationsTest {



  // Generated by ComTest BEGIN
  /** testAdd23 */
  @Test
  public void testAdd23() {    // Locations: 23
    Locations locations = new Locations(); 
    Location a1 = new Location(), a2 = new Location(); 
    assertEquals("From: Locations line: 26", 0, locations.getSize()); 
    locations.add(a1); assertEquals("From: Locations line: 27", 1, locations.getSize()); 
    locations.add(a2); assertEquals("From: Locations line: 28", 2, locations.getSize()); 
    locations.add(a1); assertEquals("From: Locations line: 29", 3, locations.getSize()); 
    assertEquals("From: Locations line: 30", a1, locations.get(0)); 
    assertEquals("From: Locations line: 31", a2, locations.get(1)); 
    assertEquals("From: Locations line: 32", a1, locations.get(2)); 
    assertEquals("From: Locations line: 33", false, locations.get(1) == a1); 
    assertEquals("From: Locations line: 34", true, locations.get(1) == a2); 
    try {
    assertEquals("From: Locations line: 35", a1, locations.get(3)); 
    fail("Locations: 35 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    locations.add(a1); assertEquals("From: Locations line: 36", 4, locations.getSize()); 
    locations.add(a1); assertEquals("From: Locations line: 37", 5, locations.getSize()); 
  } // Generated by ComTest END
}