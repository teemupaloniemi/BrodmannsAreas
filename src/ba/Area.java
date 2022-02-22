package ba;

import java.io.PrintStream;
import static kanta.CheckArea.*;

/**
 * Tietää alueen kentät: aid, nimi, lid 
 * TODO: Osaa tarkastaa tietyn kentän oikeellisuuden    
 * TODO: Osaa muuttaa "1|BA01|1|" - merkkijonon alueen tiedoiksi                                      
 * TODO: Osaa antaa merkkijonona i:n kentän tiedon      
 * TODO: Osaa laittaa merkkijonon i:neksi kentäksi      
 * 
 * @author Teemu
 * @version 21.2.2022
 *
 */
public class Area {

    private int    aid  =  0;
    private String name = "";
    private int    lid  =  0;
    
    private static int nextAid = 1;
    
    
    /**
     * Alustetaan uusi alue
     */
    public Area() {
        //
    }
    
    
    /**
     * Annetaan uudelle alueelle uniikki id
     * @return alueen viite
     */
    public Area register() {
        this.aid = nextAid;  
        nextAid++;
        return this;
    }
    
    
    
    /**
     * @return palautetaan alue id
     */
    public int getAid() {
        return this.aid;
    }
    
    
    /**
     * @return palautetaan alue id
     */
    public int getLid() {
        return this.lid;
    }
    
    
    
    /**
     * alustetaan lid
     * @param lid uusi sijainti id
     */
    public void setLid(int lid) {
       this.lid = lid;
    }
    
    
    /**
     * @return palautetaan alueen nimi
     */
    public String getName() {
        return this.name;
    }    
    
   
    /**
     * @param out tulostus tietovirta
     * @return alueen viite
     */
    public Area print(PrintStream out) {
        out.println("|id: "+ this.aid);
        out.println("|name: " + this.name);
        out.println("|location: " + this.lid);
        return this;
    }
    
    
    /**
     * täytetään oikealta näytäävällä tiedolla
     * @return alueen viite
     */
    public Area fillAreaInfo() {
        this.name = "Brodmann's area " + rand(1, 52);  
        int fakeLid = rand(0, 4);
        this.lid  = fakeLid;
        return this;
    }
    
    
    /**
     * testataan luokan toimivuus
     * @param args Ei käytössä
     */
    public static void main(String args[]) {
        Area ba1 = new Area(); 
        Area ba2 = new Area();
        Area ba3 = new Area();
       
        ba1.register().print(System.out);
        ba1.fillAreaInfo().print(System.out);

        ba2.register().print(System.out);
        ba2.fillAreaInfo().print(System.out);
        
        ba3.register().print(System.out);
        ba3.fillAreaInfo().print(System.out);
    }
}
