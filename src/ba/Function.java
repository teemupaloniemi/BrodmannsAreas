package ba;

import java.io.PrintStream;

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
     * Alustetaan uusi alue
     */
    public Function() {
        //
    }
    
    
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
    
    
    /**
     * @return tehtävän id
     */
    public int getFid() {
        return this.fid;
    }
    
   
    /**
     * @return palautetaan tehtävän nimi
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * @param out tulostus tietovirta
     */
    public void print(PrintStream out) {
        out.println(this.fid + "|" + this.name);
    }
    
    
    /**
     * Apumetodi jolla täytetään luokka oikealta näytäävällä tiedolla
     * @return viite tehtävään
     */
    public Function fillFunctionInfo() {
        this.name = CheckArea.getFunction(this.fid);  
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
       
        f1.register();
        f1.print(System.out);
        f1.fillFunctionInfo();
        f1.print(System.out);

        f2.register();
        f2.print(System.out);
        f2.fillFunctionInfo();
        f2.print(System.out);
        
        f3.register();
        f3.print(System.out);
        f3.fillFunctionInfo();
        f3.print(System.out);
    }
}
