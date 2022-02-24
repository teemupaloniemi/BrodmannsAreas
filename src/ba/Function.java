package ba;

import java.io.PrintStream;
import static kanta.CheckArea.*;

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

    private int    fid  =  0;
    private String name = "";
    
    private static int nextFid = 1;
    
    
    /**
     * Alustetaan uusi alue
     */
    public Function() {
        //
    }
    
    
    /**
     * Annetaan uudelle alueelle uniikki id
     */
    public void register() {
        this.fid = nextFid;  
        nextFid++;
    }
    
   
    /**
     * @param out tulostus tietovirta
     */
    public void print(PrintStream out) {
        out.println(this.fid + "|" + this.name);
    }
    
    
    /**
     * täytetään oikealta näytäävällä tiedolla
     */
    public void fillFunctionInfo() {
        int i = rand(0,11);
        this.name = getFunction(i);  
        this.fid  = i;
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
