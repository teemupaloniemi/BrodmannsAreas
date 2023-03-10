package ba.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import ba.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.03.31 13:48:24 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class BaTest {



  // Generated by ComTest BEGIN
  /** 
   * testAdd32 
   * @throws TilaException when error
   */
  @Test
  public void testAdd32() throws TilaException {    // Ba: 32
    Ba ba = new Ba(); 
    Area a1 = new Area().register().setName("Brodmann's Area 3"); 
    Area a2 = new Area().register().setName("Brodmann's Area 17"); 
    a1.register(); a2.register(); 
    assertEquals("From: Ba line: 38", 0, ba.getAreaCount()); 
    ba.add(a1); assertEquals("From: Ba line: 39", 1, ba.getAreaCount()); 
    ba.add(a2); assertEquals("From: Ba line: 40", 2, ba.getAreaCount()); 
    try {
    ba.add(a1); 
    fail("Ba: 41 Did not throw TilaException");
    } catch(TilaException _e_){ _e_.getMessage(); } // alue on jo olemassa
    assertEquals("From: Ba line: 42", a1, ba.getArea(0)); 
    assertEquals("From: Ba line: 43", a2, ba.getArea(1)); 
    try {
    assertEquals("From: Ba line: 44", a1, ba.getArea(3)); 
    fail("Ba: 44 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    ba.add(new Area().register().setName("Brodmann's Area 100")); assertEquals("From: Ba line: 45", 3, ba.getAreaCount()); 
    ba.add(new Area().register().setName("Brodmann's Area 890")); assertEquals("From: Ba line: 46", 4, ba.getAreaCount()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testAdd58 
   * @throws TilaException when error
   */
  @Test
  public void testAdd58() throws TilaException {    // Ba: 58
    Ba ba = new Ba(); 
    Function f1 = new Function().register().setName("Working memory"); 
    Function f2 = new Function().register().setName("Declarative memory"); 
    assertEquals("From: Ba line: 63", 0, ba.getFunctionCount()); 
    ba.add(f1); assertEquals("From: Ba line: 64", 1, ba.getFunctionCount()); 
    ba.add(f2); assertEquals("From: Ba line: 65", 2, ba.getFunctionCount()); 
    try {
    ba.add(f1); 
    fail("Ba: 66 Did not throw TilaException");
    } catch(TilaException _e_){ _e_.getMessage(); } // funktio on jo olemassa
    assertEquals("From: Ba line: 67", f1, ba.getFunction(0)); 
    assertEquals("From: Ba line: 68", f2, ba.getFunction(1)); 
    try {
    assertEquals("From: Ba line: 69", f1, ba.getFunction(3)); 
    fail("Ba: 69 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    ba.add(new Function().register().setName("Sense of pain")); assertEquals("From: Ba line: 70", 3, ba.getFunctionCount()); 
    ba.add(new Function().register().setName("Move hands")); assertEquals("From: Ba line: 71", 4, ba.getFunctionCount()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testAdd83 
   * @throws TilaException when error
   */
  @Test
  public void testAdd83() throws TilaException {    // Ba: 83
    Ba ba = new Ba(); 
    Location l1 = new Location().setName("Motor cortex").setLid(1); 
    Location l2 = new Location().setName("Visual cortex").setLid(2); 
    ba.add(l1); 
    ba.add(l2); 
    Function f1 = new Function().setName("Working memory").setFid(1); 
    Function f2 = new Function().setName("Declarative memory").setFid(2); 
    Function f3 = new Function().setName("Move hands").setFid(3); 
    ba.add(f1); 
    ba.add(f2); 
    ba.add(f3); 
    Lf lf1 = new Lf(1,1); 
    Lf lf2 = new Lf(2,2); 
    Lf lf3 = new Lf(1,3); 
    assertEquals("From: Ba line: 103", 0, ba.getLfCount()); 
    ba.add(lf1); assertEquals("From: Ba line: 105", 1, ba.getLfCount()); 
    ba.add(lf2); assertEquals("From: Ba line: 106", 2, ba.getLfCount()); 
    ba.add(lf3); assertEquals("From: Ba line: 107", 3, ba.getLfCount()); 
    try {
    ba.add(new Lf(1,1)); 
    fail("Ba: 108 Did not throw TilaException");
    } catch(TilaException _e_){ _e_.getMessage(); } //jo olemasso oleva pari
    assertEquals("From: Ba line: 110", 1, ba.findLocationFor(3).getID()); 
    var list = ba.findFunctions(1); 
    assertEquals("From: Ba line: 113", "Working memory", list.get(0).getName()); 
    assertEquals("From: Ba line: 114", "Move hands", list.get(1).getName()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testAdd127 
   * @throws TilaException when error
   */
  @Test
  public void testAdd127() throws TilaException {    // Ba: 127
    Ba ba = new Ba(); 
    Location l1 = new Location().register().setName("Motor cortex"); 
    Location l2 = new Location().register().setName("Visual cortex"); 
    assertEquals("From: Ba line: 132", 0, ba.getLocationCount()); 
    ba.add(l1); assertEquals("From: Ba line: 133", 1, ba.getLocationCount()); 
    ba.add(l2); assertEquals("From: Ba line: 134", 2, ba.getLocationCount()); 
    try {
    ba.add(l1); 
    fail("Ba: 135 Did not throw TilaException");
    } catch(TilaException _e_){ _e_.getMessage(); } // funktio on jo olemassa
    assertEquals("From: Ba line: 136", l1, ba.getLocation(0)); 
    assertEquals("From: Ba line: 137", l2, ba.getLocation(1)); 
    try {
    assertEquals("From: Ba line: 138", l1, ba.getLocation(3)); 
    fail("Ba: 138 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    ba.add(new Location().register().setName("Temporal lobe")); assertEquals("From: Ba line: 139", 3, ba.getLocationCount()); 
    ba.add(new Location().register().setName("Wernicke's area")); assertEquals("From: Ba line: 140", 4, ba.getLocationCount()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testAdd152 
   * @throws TilaException when error
   */
  @Test
  public void testAdd152() throws TilaException {    // Ba: 152
    try {
    Ba ba = new Ba(); 
    Area a1 = new Area().register(); 
    Area a2 = new Area().register(); 
    Area a3 = new Area().register(); 
    ba.add(a1); 
    ba.add(a2); 
    ba.add(a3); 
    Neighbour n2 = new Neighbour(0,1); 
    Neighbour n3 = new Neighbour(2,1); 
    assertEquals("From: Ba line: 164", 0, ba.getNeighbourCount()); 
    ba.add(n2); assertEquals("From: Ba line: 165", 1, ba.getNeighbourCount()); 
    ba.add(n3); assertEquals("From: Ba line: 166", 2, ba.getNeighbourCount()); 
    try {
    ba.add(new Neighbour(0,0)); 
    fail("Ba: 167 Did not throw TilaException");
    } catch(TilaException _e_){ _e_.getMessage(); } // Ei voi olla itsens?? naapuri
    try {
    ba.add(new Neighbour(1,2)); 
    fail("Ba: 168 Did not throw TilaException");
    } catch(TilaException _e_){ _e_.getMessage(); } // Jo olemasso oleva pari
    ba.add(new Neighbour(0,2)); assertEquals("From: Ba line: 169", 3, ba.getNeighbourCount()); 
    } catch (TilaException e) { //
    }
  } // Generated by ComTest END
}