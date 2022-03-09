package ba;

import java.io.PrintStream;

/** 
 * Tietää sijainnin kentät: lid, nimi                
 * Osaa tarkastaa tietyn kentän oikeellisuuden      
 * Osaa muuttaa merkkijonon "3|Angular gyrus - Wernicke's Location" sijainti tiedoiksi
 * Osaa antaa merkkijonona i:n kentän tiedon        
 * Osaa laittaa merkkijonon i:neksi kentäksi             
 * 
 * @author Teemu
 * @version 21.2.2022
 *
 */
public class Location {

    private int    lid  =  -1;
    private String name = "";
    
    private static int nextLid = 0;
    
    
    /**
     * @return sijainnin nimi
     */
    public String getName() {
        return this.name;
    }
    
    
    /**
     * @return sijainnin id
     */
    public int getLid() {
        return this.lid;
    }
    
    
    /**
     * Annetaan uudelle alueelle uniikki id
     * @return viitteen sijaintiin
     * @example
     * <pre name="test">
     * Location a1 = new Location();
     * Location a2 = new Location();
     * a1.register().getLid() != -1 === true;
     * a2.register().getLid() != a1.getLid() === true;
     * </pre>
     */
    public Location register() {
        this.lid = nextLid;  
        nextLid++;
        return this;
    }
    
   
    /**
     * @param out tulostus tietovirta
     */
    public void print(PrintStream out) {
        out.println(this.lid + "|" + this.name);
    }
    
    
    /**
     * Apumetodi jolla täytetään luokka oikealta näytäävällä tiedolla
     * @return viite sijaintiin
     */
    public Location fillLocationInfo() {
        int i = kanta.CheckArea.rand(0, 4);
        this.name = kanta.CheckArea.getLocation(i);  
        return this;
    }
    
    
    /**
     * testataan luokan toimivuus
     * @param args Ei käytössä
     */
    public static void main(String args[]) {
        Location l1 = new Location(); 
        Location l2 = new Location();
        Location l3 = new Location();
       
        l1.register().print(System.out);
        l1.fillLocationInfo();
        l1.print(System.out);

        l2.register();
        l2.print(System.out);
        l2.fillLocationInfo();
        l2.print(System.out);
        
        l3.register();
        l3.print(System.out);
        l3.fillLocationInfo();
        l3.print(System.out);
    }
}

