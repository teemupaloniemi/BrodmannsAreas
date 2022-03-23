package ba;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

import static kanta.CheckArea.*;

/**
 * 
 * @author Teemu
 * @version 21.2.2022
 */
public class Area implements Olio, Cloneable {

    private int    aid  = -1;  // alueen indeksi numero
    private String name = "";  // alueen nimi
    private int    lid  = -1;  // lohkon id missä alue sijaitsee
    
    private static int nextAid = 0; // rekisteröinti laskuri 
    
    
    @Override
    public Area clone() throws CloneNotSupportedException {
        return (Area) super.clone();
    }
    
    
    /**
     * Apumetodi jolla täytetään luokka oikealta näytäävällä tiedolla
     * @return alueen viite
     */
    public Area fillAreaInfo() {
        this.name = "Brodmann's area " + rand(1, 52);  
        int fakeLid = rand(0, 4);
        this.setLid(fakeLid);
        return this;
    }
    
    
    /**
     * @return palautetaan alue id
     * @example
     * <pre name="test">
     * Area a1 = new Area();
     * a1.setAid(199); 
     * a1.getID() === 199;
     * </pre>
     */
    @Override
    public int getID() {
        return this.aid;
    }
    
    
    /**
     * @return palautetaan lohko id
     * @example
     * <pre name="test">
     * Area a1 = new Area();
     * a1.setLid(199); 
     * a1.getLid() === 199;
     * </pre>
     */
    public int getLid() {
        return this.lid;
    }
    
    
    /**
     * @return palautetaan alueen nimi
     * @example
     * <pre name="test">
     * Area a = new Area().parse("10|Brodmann's Area 15|8");
     * a.getName() === "Brodmann's Area 15";
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
     *   Area area = new Area();
     *   area.parse(" 2 | Brodmann's Area 25 | 13 ");
     *   area.getID() === 2;
     *   area.getLid() === 13;
     *   area.toString() === "2|Brodmann's Area 25|13";
     * </pre>
     */
    public Area parse(String s) {
        StringBuffer sb = new StringBuffer(s);
        this.setAid(Mjonot.erota(sb, '|', this.getID())); // ensimmäistä tolppaa edeltävä tieto 
        this.name = Mjonot.erota(sb, '|', this.getName()); // toista tolppaa edeltävä tieto
        this.setLid(Mjonot.erota(sb, '|', this.getLid())); // toisen tolpan jälkeinen tieto 
        return this; 
    }
    
    
    /**
     * @param os tulostus tietovirta
     * @return alueen viite
     * //@example
     * //<pre name="test">
     * //#import java.io.ByteArrayOutputStream;
     * //ByteArrayOutputStream outContent = new ByteArrayOutputStream();
     * //Area a = new Area().fillAreaInfo().register();
     * //a.print(outContent);
     * //outContent.toString() =R= "\\d\\+|Brodmann's Area \\d\\+|\\d\\+" + System.lineSeparator();
     * //</pre>
     * Tee testit toimiviksi regexpilla
     */
    public Area print(OutputStream os) {
        PrintStream out = new PrintStream(os); 
        out.println(this.toString());
        return this;
    }
    
    
    /**
     * Annetaan uudelle alueelle uniikki id
     * @return alueen viite
     * @example
     * <pre name="test">
     * Area a1 = new Area();
     * Area a2 = new Area();
     * a1.register().getID() != -1 === true;
     * a2.register().getID() != a1.getID() === true;
     * </pre>
     */
    public Area register() {
        this.aid = nextAid;  
        nextAid++; // kasvatetaan sueraavaa varten 
        return this; // ketjutus
    } 
    
    
    /**
     * alustetaan aid
     * @param aid id joka annetaan
     * @return palautetaan olioviite
     * @example
     * <pre name="test">
     * Area a1 = new Area();
     * a1.setAid(199); 
     * a1.getID() === 199;
     * </pre>
     */
    public Area setAid(int aid) {
        this.aid = aid;
        return this;
    }
    
    
    /**
     * Muutetaan alueen nimen 
     * @param name nimi joka annetaan
     * @return palauttaa alueen viitteen
     * @throws TilaException jos nimi ei oikeaa muotoa
     */
    public Area setName(String name) throws TilaException {
        if (!name.matches("Brodmann's Area \\d+")) throw new TilaException("Tarkista nimen kirjoitus!");
        this.name = name;
        return this;
    }
    
    
    /**
     * alustetaan lid
     * @param lid uusi lohko id
     * @return alueen viite
     * @example
     * <pre name="test">
     * new Area().setLid(1).getLid() === 1;
     * new Area().setLid(3).getLid() === 3;
     * </pre>
     */
    public Area setLid(int lid) {
       this.lid = lid;
       return this;
    } 
    
    
    /**
     * muutetaan seuraavaa id numeroa (siihen mihin se viimeksi jäi)
     * @param i luku johon id vaihdetaan
     * @example
     * <pre name="test">
     * Area a1 = new Area();
     * Area a2 = new Area();
     * a1.register().getID() != -1 === true;
     * Area.setNextAid(2);
     * a2.register().getID() === 2;
     * </pre>
     */
    public static void setNextAid(int i) {
        nextAid = i; 
    }
    
    
    @Override
    public String toString() {
        return this.aid + "|" + this.name + "|" + this.lid;
    }
    
    
    /**
     * testataan luokan toimivuus
     * @param args Ei käytössä
     */
    public static void main(String args[]) {
        Area ba1 = new Area();
        Area ba2 = new Area();
        Area ba3 = new Area();
       
        ba1.register().print(System.out); //rekisteröidään ei tietoja vielä 
        ba1.fillAreaInfo().print(System.out); //satunnaiset tiedot 
        ba1.parse(ba1.getID()+"|Brodmann's Area 24|12").print(System.out); //tiedot syötteestä
        
        ba2.register().print(System.out); //rekisteröidään ei tietoja vielä 
        ba2.fillAreaInfo().print(System.out); //satunnaiset tiedot 
        ba2.parse(ba2.getID()+"|Brodmann's Area 52|14").print(System.out);        
        
        
        ba3.register().print(System.out); //rekisteröidään ei tietoja vielä 
        ba3.fillAreaInfo().print(System.out); //satunnaiset tiedot 
        ba3.parse(ba3.getID()+"|Brodmann's Area 1|6").print(System.out); //tiedot syötteestä
    }
}
