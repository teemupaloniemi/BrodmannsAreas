package ba;

import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

import static kanta.CheckArea.*;

/**
 * 
 * @author Teemu
 * @version 21.2.2022
 */
public class Area {

    private int    aid  =  -1;
    private String name = "";
    private int    lid  =  0;
    
    private static int nextAid = 0;
    
    /**
     * Annetaan uudelle alueelle uniikki id
     * @return alueen viite
     * @example
     * <pre name="test">
     * Area a1 = new Area();
     * Area a2 = new Area();
     * a1.register().getAid() != -1 === true;
     * a2.register().getAid() != a1.getAid() === true;
     * </pre>
     */
    public Area register() {
        this.aid = nextAid;  
        nextAid++;
        return this;
    } 
    
    
    /**
     * muutetaan seuraavaa id numeroa
     * @param i luku johon id vaihdetaan
     */
    public void setNextAid(int i) {
        nextAid = i;
    }
    
    /**
     * @return palautetaan alue id
     */
    public int getAid() {
        return this.aid;
    }
    
    
    /**
     * @return palautetaan alueen nimi
     */
    public String getName() {
        return this.name;
    } 
    
    
    /**
     * @return palautetaan sijainti id
     */
    public int getLid() {
        return this.lid;
    }
    
    
    @Override
    public String toString() {
        return this.aid + "|" + this.name + "|" + this.lid;
    }
    
    
    /**
     * alustetaan lid
     * @param lid uusi sijainti id
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
     * alustetaan aid
     * @param aid id joka annetaan
     * @return palautetaan olioviite
     */
    public Area setAid(int aid) {
        this.aid = aid;
        return this;
    }
    
   
    /**
     * @param out tulostus tietovirta
     * @return alueen viite
     */
    public Area print(PrintStream out) {
        out.println(this.aid + "|" + this.name + "|" + this.lid);
        return this;
    }
    
    
    /**
     * Apumetodi jolla täytetään luokka oikealta näytäävällä tiedolla
     * @return alueen viite
     */
    public Area fillAreaInfo() {
        this.name = "Brodmann's area " + rand(1, 52);  
        int fakeLid = rand(0, 4);
        this.lid  = fakeLid;
        return this;
    }


    /**
     * muutetaan merkkijono luokan tiedoiksi
     * @param s merkkijono jota tutkitaan
     */
    public void parse(String s) {
        StringBuffer sb = new StringBuffer(s);
        this.setAid(Mjonot.erota(sb, '|', this.getAid()));
        this.name = Mjonot.erota(sb, '|', this.getName());
        this.setLid(Mjonot.erota(sb, '|', this.getLid()));
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
