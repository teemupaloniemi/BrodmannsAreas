package ba;

import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/** 
 * Tietää sijainnin kentät: lid, nimi                
 * Osaa tarkastaa tietyn kentän oikeellisuuden      
 * Osaa muuttaa merkkijonon "3|Angular gyrus - Wernicke's Location" lohko tiedoiksi
 * Osaa antaa merkkijonona i:n kentän tiedon        
 * Osaa laittaa merkkijonon i:neksi kentäksi             
 * 
 * @author Teemu
 * @version 21.2.2022
 *
 */
public class Location implements Tietue {

    private int    lid  =  -1;
    private String name = "";
    
    private static int nextLid = 0;
    
    
    /**
     * Apumetodi jolla täytetään luokka oikealta näytäävällä tiedolla
     * @return viite lohkoin
     */
    public Location fillLocationInfo() {
        int i = kanta.CheckArea.rand(0, 4);
        this.name = kanta.CheckArea.getLocation(i);  
        return this;
    }
    
    
    /**
     * @return sijainnin id
     * @example
     * <pre name="test">
     * Location l1 = new Location();
     * l1.setLid(199); 
     * l1.getID() === 199;
     * </pre>
     */
    @Override
    public int getID() {
        return this.lid;
    }

    
    /**
     * @return sijainnin nimi
     * @example
     * <pre name="test">
     * Location l = new Location().parse("10|Secondary motor cortex");
     * l.getName() === "Secondary motor cortex";
     * </pre>
     */
    public String getName() {
        return this.name;
    }
    
    
    /**
     * muutetaan merkkijono luokan tiedoiksi
     * @param s merkkijono jota tutkitaan
     * @return olion viitteen
     * @example
     * <pre name="test">
     *   Location l = new Location();
     *   l.parse(" 7 | Temporal lobe ");
     *   l.getID() === 7;
     *   l.toString() === "7|Temporal lobe";
     * </pre>
     */
    public Location parse(String s) {
        StringBuffer sb = new StringBuffer(s);
        this.setLid(Mjonot.erota(sb, '|', this.getID()));
        this.name = Mjonot.erota(sb, '|', this.getName());
        return this;
    }
    
    
    /**
     * @param out tulostus tietovirta
     * //@example
     * //<pre name="test">
     * //#import java.io.ByteArrayOutputStream;
     * //ByteArrayOutputStream outContent = new ByteArrayOutputStream();
     * //Location l = new Location().fillLocationInfo().register();
     * //l.print(outContent);
     * //outContent.toString() =R= "\\d\\+|[a-zA-Z]+" + System.lineSeparator();
     * //</pre>
     * Tee testit toimiviksi regexpilla
     */
    public void print(PrintStream out) {
        out.println(this.toString());
    }
    
    
    /**
     * Annetaan uudelle alueelle uniikki id
     * @return viitteen lohkoin
     * @example
     * <pre name="test">
     * Location a1 = new Location();
     * Location a2 = new Location();
     * a1.register().getID() != -1 === true;
     * a2.register().getID() != a1.getID() === true;
     * </pre>
     */
    public Location register() {
        this.lid = nextLid;  
        nextLid++;
        return this;
    }
    
    
    /**
     * asetetaan uusi id
     * @param lid asetetttava id
     * @return olion viitteen
     * @example
     * <pre name="test">
     * Location l1 = new Location();
     * l1.setLid(199); 
     * l1.getID() === 199;
     * </pre>
     */
    public Location setLid(int lid) {
        this.lid = lid;
        return this;
    }
    
    
    /**
     * muutetaan nimi
     * @param name muutettu nimi 
     * @return funtion 
     */
    public Location setName(String name) {
        this.name = name;
        return this;
    }
    
    /**
     * muutetaan seuraavaa id numeroa
     * @param i miksi muutetaan
     * @example
     * <pre name="test">
     * Location l1 = new Location();
     * Location l2 = new Location();
     * l1.register().getID() != -1 === true;
     * Location.setNextLid(2);
     * l2.register().getID() === 2;
     * </pre>
     */
    public static void setNextLid(int i) {
        nextLid = i;
    }
    

    @Override
    public String toString() {
        return this.getID() + "|" + this.getName();
    }
    
    
    /**
     * testataan luokan toimivuus
     * @param args Ei käytössä
     */
    public static void main(String args[]) {
        Location l1 = new Location();
        Location l2 = new Location();
        Location l3 = new Location();
       
        l1.register().print(System.out); //rekisteröidään ei tietoja vielä 
        l1.fillLocationInfo().print(System.out); //satunnaiset tiedot 
        l1.parse(l1.getID()+"|Primary motor cortex").print(System.out); //tiedot syötteestä
        
        l2.register().print(System.out); //rekisteröidään ei tietoja vielä 
        l2.fillLocationInfo().print(System.out); //satunnaiset tiedot 
        l2.parse(l2.getID()+"|Superior parietal lobule").print(System.out);        
        
        
        l3.register().print(System.out); //rekisteröidään ei tietoja vielä 
        l3.fillLocationInfo().print(System.out); //satunnaiset tiedot 
        l3.parse(l3.getID()+"|Secondary sensorimotor cortex").print(System.out); //tiedot syötteestä
    }
}