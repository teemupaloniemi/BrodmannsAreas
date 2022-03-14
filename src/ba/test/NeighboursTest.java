package ba.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import ba.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.03.14 20:16:38 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class NeighboursTest {



  // Generated by ComTest BEGIN
  /** testNeighbours16 */
  @Test
  public void testNeighbours16() {    // Neighbours: 16
    try {
    Neighbours neighbours = new Neighbours(); 
    Neighbour neighbour1 = new Neighbour(1,2); 
    Neighbour neighbour2 = new Neighbour(1,3); 
    assertEquals("From: Neighbours line: 21", 0, neighbours.getSize()); 
    neighbours.add(neighbour1); assertEquals("From: Neighbours line: 22", 1, neighbours.getSize()); 
    neighbours.add(neighbour2); assertEquals("From: Neighbours line: 23", 2, neighbours.getSize()); 
    neighbours.add(neighbour1); assertEquals("From: Neighbours line: 24", 3, neighbours.getSize()); 
    try {
    neighbours.add(new Neighbour(2,1)); 
    fail("Neighbours: 25 Did not throw TilaException");
    } catch(TilaException _e_){ _e_.getMessage(); }
    try {
    neighbours.add(new Neighbour(3,3)); 
    fail("Neighbours: 26 Did not throw TilaException");
    } catch(TilaException _e_){ _e_.getMessage(); }
    assertEquals("From: Neighbours line: 27", neighbour1, neighbours.get(0)); 
    assertEquals("From: Neighbours line: 28", neighbour2, neighbours.get(1)); 
    assertEquals("From: Neighbours line: 29", neighbour1, neighbours.get(2)); 
    assertEquals("From: Neighbours line: 30", false, neighbours.get(1) == neighbour1); 
    assertEquals("From: Neighbours line: 31", true, neighbours.get(1) == neighbour2); 
    neighbours.add(neighbour1); assertEquals("From: Neighbours line: 32", 4, neighbours.getSize()); 
    neighbours.add(neighbour2); assertEquals("From: Neighbours line: 33", 5, neighbours.getSize()); 
    } catch (Exception e) {/* */}
  } // Generated by ComTest END
}