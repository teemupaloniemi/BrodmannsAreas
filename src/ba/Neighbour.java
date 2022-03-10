package ba;

import java.io.PrintStream;

/**
 * @author Teemu
 * @version 8.3.2022
 *
 */
public class Neighbour {
    private final int a1;
    private final int a2;
       
    /**
     * Alustetaan alue pari 
     * @param a1 eka alue
     * @param a2 toinen alue 
     * @throws TilaException jos yritetään luoda naapuria virheellisillä arvoilla
     */
    public Neighbour (int a1, int a2) throws TilaException{
        if (a1 == a2) throw new TilaException("Tottakai itsensä pari senkin hölmö!");
        this.a1 = a1; 
        this.a2 = a2;
    }
    
    
    @Override
    public String toString() {
        return this.getAreaFirst() + "|" + this.getAreaSecond();
    }
    
    
    @Override
    public int hashCode() {
       return Integer.valueOf(""+this.a1+this.a2);
    }
    
    
    /**
     * Palautetaan sijainti id
     * @return sijainti id
     */
    public int getAreaFirst() {
        return this.a1;
    }
    
    
    /**
     * Palautetaan tehtävä id
     * @return tehtävä id
     */
    public int getAreaSecond() {
        return this.a2;
    }
    
    
    /**
     * @param out tulostus tietovirta
     */
    public void print(PrintStream out) {
        out.println(this.a1 + "|" + this.a2);
    }
    
}