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
        if (a1 == a2) throw new TilaException("Onko Aku Ankka itsensä naapuri?");
        this.a1 = a1; 
        this.a2 = a2; 
    }
    
    
    /**
     * onko jompi kumpi pareista etsittävä 
     * @param aid etsittävän alueen id
     * @return true jos jompi kumpi on sama kuin etsittävä
     */
    public boolean contains(int aid) {
        if (this.getAreaFirst() == aid || this.getAreaSecond() == aid) return true;
        return false;
    }
    
    
    /**
     * Palautetaan lohko id
     * @return lohko id
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
     * annetaan aidn kaveri 
     * @param aid alue id jonka kaveria etsitään
     * @return alueen keveri
     */
    public int getOpposite(int aid) {
        if (this.getAreaFirst() == aid) return this.getAreaSecond();
        return this.getAreaFirst();
    }
    
    
    @Override
    public int hashCode() {
       int small = this.a2; 
       int large = this.a1;
       if (this.a1 < this.a2) { small = this.a1; large = this.a2; } // jos tarvitsee vaihtaa vaihdetaan
       return Integer.valueOf(""+small+large); // järjestys aina pienempi|suurempi
    }
    
    
    /**
     * @param out tulostus tietovirta
     */
    public void print(PrintStream out) {
        out.println(this.toString());
    }
    
    
    @Override
    public String toString() {
        return this.getAreaFirst() + "|" + this.getAreaSecond();
    }  
}