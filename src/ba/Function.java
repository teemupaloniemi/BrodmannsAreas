package ba;

import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;
import kanta.CheckArea;

/** 
 * Tietää toimiinnon kentät: fid, nimi             
 * Osaa tarkastaa tietyn kentän oikeellisuuden     
 * Osaa muuttaa " 39|Arithmetics " - merkkijonon tehtävän tiedoiksi                              
 * Osaa antaa merkkijonona i:n kentän tiedon       
 * Osaa laittaa merkkijonon i:neksi kentäksi                
 * 
 * @author Teemu
 * @version 21.2.2022
 *
 */
public class Function {

    private int    fid  =  -1;
    private String name = "";
    
    private static int nextFid = 0;
    
    
    /**
     * Annetaan uudelle alueelle uniikki id
     * @return viite tetävään
     * @example
     * <pre name="test">
     * Function f1 = new Function();
     * Function f2 = new Function();
     * f1.register().getFid() != -1 === true;
     * f2.register().getFid() != f1.getFid() === true;
     * </pre>
     */
    public Function register() {
        this.fid = nextFid;  
        nextFid++;
        return this;
    }
    
    
    @Override
    public String toString() {
        return this.getFid() + "|" + this.getName();
    }
    
    
    /**
     * @return tehtävän id
     * @example
     * <pre name="test">
     * Function f1 = new Function();
     * f1.setFid(199); 
     * f1.getFid() === 199;
     * </pre>
     */
    public int getFid() {
        return this.fid;
    }
    
    
    /**
     * asetetaan seuraava id uudeksi 
     * @param i miksi id muutetaan
     * @example
     * <pre name="test">
     * Function f1 = new Function();
     * Function f2 = new Function();
     * f1.register().getFid() != -1 === true;
     * Function.setNextFid(2);
     * f2.register().getFid() === 2;
     * </pre>
     */
    public static void setNextFid(int i) {
        nextFid = i;
    }
    
    
    /**
     * @param fid asetetaan uusi id
     * @example
     * <pre name="test">
     * Function f1 = new Function();
     * f1.setFid(199); 
     * f1.getFid() === 199;
     * </pre>
     */
    public void setFid(int fid) {
        this.fid = fid;
    }
    
   
    /**
     * @return palautetaan tehtävän nimi
     * @example
     * <pre name="test">
     * Function f = new Function().parse("10|Grammar");
     * f.getName() === "Grammar";
     * </pre>
     */
    public String getName() {
        return this.name;
    }

    
    /**
     * @param out tulostus tietovirta
     * //@example
     * //<pre name="test">
     * //#import java.io.ByteArrayOutputStream;
     * //ByteArrayOutputStream outContent = new ByteArrayOutputStream();
     * //Function f = new Function().fillFunctionInfo().register();
     * //f.print(outContent);
     * //outContent.toString() =R= "\\d\\+|[a-zA-Z]+" + System.lineSeparator();
     * //</pre>
     * Tee testit toimiviksi regexpilla
     */
    public void print(PrintStream out) {
        out.println(this.fid + "|" + this.name);
    }
    
    
    /**
     * Apumetodi jolla täytetään luokka oikealta näytäävällä tiedolla
     * @return viite tehtävään
     */
    public Function fillFunctionInfo() {
        this.name = CheckArea.getFunction(CheckArea.rand(0,22));  
        return this;
    }
    
    
    /**
     * muutetaan merkkijono luokan tiedoiksi
     * @param s merkkijono jota tutkitaan
     * @return olion viitteen 
     * @example
     * <pre name="test">
     *   Function f = new Function();
     *   f.parse(" 2 | Localize pain");
     *   f.getFid() === 2;
     *   f.toString() === "2|Localize pain";
     * </pre>
     */
    public Function parse(String s) {
        StringBuffer sb = new StringBuffer(s);
        this.setFid(Mjonot.erota(sb, '|', this.getFid()));
        this.name = Mjonot.erota(sb, '|', this.getName());
        return this;
    }
    
    
    /**
     * testataan luokan toimivuus
     * @param args Ei käytössä
     */
    public static void main(String args[]) {
        Function f1 = new Function();
        Function f2 = new Function();
        Function f3 = new Function();
       
        f1.register().print(System.out); //rekisteröidään ei tietoja vielä 
        f1.fillFunctionInfo().print(System.out); //satunnaiset tiedot 
        f1.parse(f1.getFid()+"|Sense of fingers").print(System.out); //tiedot syötteestä
        
        f2.register().print(System.out); //rekisteröidään ei tietoja vielä 
        f2.fillFunctionInfo().print(System.out); //satunnaiset tiedot 
        f2.parse(f2.getFid()+"|Anticipate tickling").print(System.out);        
        
        f3.register().print(System.out); //rekisteröidään ei tietoja vielä 
        f3.fillFunctionInfo().print(System.out); //satunnaiset tiedot 
        f3.parse(f3.getFid()+"|Move hands").print(System.out); //tiedot syötteestä
        
    }
}
