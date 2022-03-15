package ba;

/**
 * @author Teemu
 * @version 14.3.2022
 *
 */
public interface Tietorakenne {
    
    /**
     * @param i indeksi josta objektia haetaan 
     * @return objektin paikassa i 
     */
    public Object get(int i); 
    
    
    /**
     * @return tallennettava tiedoston nimen
     */
    public String getFileName();
    
    
    /**
     * @return tietorakenteen koko
     */
    public int getSize();
    
    
    /**
     * @return true jos tietorakennetta muutettu 
     */
    public boolean isAltered();
    
    
    /**
     * palutetaan alkutilanteeseen
     */
    public void resetAltered();
}
