package ba.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import ba.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.03.01 23:40:12 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class LocationTest {



  // Generated by ComTest BEGIN
  /** testRegister44 */
  @Test
  public void testRegister44() {    // Location: 44
    Location a1 = new Location(); 
    Location a2 = new Location(); 
    assertEquals("From: Location line: 47", true, a1.register().getLid() != 0); 
    assertEquals("From: Location line: 48", true, a2.register().getLid() != a1.getLid()); 
  } // Generated by ComTest END
}