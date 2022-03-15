package ba;

import java.io.PrintStream;

/**
 * @author Teemu
 * @version 8.3.2022
 *
 */ 
public class Lf {
    private final int location;
    private final int function;
       
    /**
     * Alustetaan location function pari 
     * @param location hinta
     * @param function paino 
     */
    public Lf(int location, int function) {
        this.location = location; 
        this.function = function; 
    }

    
    /**
     * Palautetaan tehtävä id
     * @return tehtävä id
     */
    public int getFunction() {
        return this.function;
    }
    
    
    /**
     * Palautetaan sijainti id
     * @return sijainti id
     */
    public int getLocation() {
        return this.location;
    }
    
    
    @Override
    public int hashCode() {
        return Integer.valueOf(""+this.getLocation()+this.getFunction());
    }
    
    
    /**
     * @param out tulostus tietovirta
     */
    public void print(PrintStream out) {
        out.println(this.toString());
    }
    
    
    @Override
    public String toString() {
        return this.getLocation() + "|" + this.getFunction();
    }   
}