package ba;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Teemu
 * @version 21.2.2022
 * 
 * @example
 * <pre name="test">
 * Functions functions = new Functions();
 * Function a1 = new Function(), a2 = new Function();
 * functions.getSize() === 0;
 * functions.add(a1); functions.getSize() === 1;
 * functions.add(a2); functions.getSize() === 2;
 * functions.add(a1); functions.getSize() === 3;
 * functions.get(0) === a1;
 * functions.get(1) === a2;
 * functions.get(2) === a1;
 * functions.get(1) == a1 === false;
 * functions.get(1) == a2 === true; 
 * functions.add(a1); functions.getSize() === 4;
 * functions.add(a1); functions.getSize() === 5;
 * </pre>
 */
public class Functions implements Tietorakenne {  
     private ArrayList<Function> functions = new ArrayList<Function>();
     private boolean altered = false;
     private String fileName = "function.dat";
     
     
    /**
     * Lisätään uusi alue aluistoon
     * @param function alue joka lisätään
     */
     public void add(Function function){
         this.functions.add(function);
         altered = true;
     }
     
     
     /**
      * @param i alkion indeksi
      * @return halutun alkion
      */
     @Override
    public Function get(int i) {
         if (0 > i || i >= this.getSize()) throw new IndexOutOfBoundsException("Laiton indeksi fuktiota etsittäessä: " + i);
         return functions.get(i);
     }
     
     
     /**
      * @return palautetaan tiedostonimi
      */
     @Override
    public String getFileName() {
          return this.fileName;
      }
     
     
     /**
      * @return paljonko alkiota 
      */
     @Override
    public int getSize() {
         return functions.size();
     }
     
     
     /**
      * @return onko tiedostoa muutettu, true jos on 
      */
     @Override
     public boolean isAltered() {
         return this.altered;
     }
     
     
     /**
      * @param name hakemiston nimi josta luetaan
      * @throws TilaException jos ongelmia hakemisessa
      */
     public void readFile(String name) throws TilaException {
         String file = name + "\\" + this.getFileName();
         File f = new File(file);
         try (Scanner fi = new Scanner(new FileInputStream(f))) { // Jotta UTF8/ISO-8859 toimii'
             while ( fi.hasNext() ) {
                 String s = fi.nextLine().trim();
                 if ( s == null || "".equals(s) || s.charAt(0) == '#' ) continue;
                 Function function = new Function();
                 function.parse(s);
                 this.add(function);
             }
             Function.setNextFid(this.get(this.getSize()-1).getFid()+1);  
         } catch ( FileNotFoundException e ) {
             throw new TilaException("Ei saa luettua tiedostoa " + file);
         }
     }

     
     /**
      * palutetaan alkutilanteeseen
      */
     @Override
     public void resetAltered() {
         this.altered = false;
     }
     

     /**
      * vaihdetaan tiedostonimiä (lähinnä testitiedoston luomiseen)
      * @param s tiednimi
      */
     public void setFileName(String s) {
         this.fileName = s;
     }
     
     
     /**
      * Testiohjelma alueille
      * @param args ei käytössä
      */
     public static void main(String args[]) {
         Functions functions = new Functions();
         functions.add(new Function().register().fillFunctionInfo());
         functions.add(new Function().register().fillFunctionInfo());
         functions.add(new Function().register().fillFunctionInfo());
         functions.add(new Function().register().fillFunctionInfo());

         System.out.println("============= Function testi =================");
         
         for (int i = 0; i < functions.getSize(); i++) functions.get(i).print(System.out);
     }
}
