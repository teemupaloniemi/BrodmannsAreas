package ba;

import java.util.ArrayList;

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
public class Functions implements TietorakenneJuoksevallaID {  
     private ArrayList<Function> functions = new ArrayList<Function>();
     private boolean altered = false;
     private String fileName = "function.dat";
     
     
    /**
     * Lisätään uusi alue aluistoon
     * @param function alue joka lisätään
     */
     @Override
    public void add(Object function){
         this.functions.add((Function) function);
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
     * @param name nimi jota etsitään
     * @return löydetyn funktion tai luo uuden jos ei löydy 
     */
    public Function get(String name) {
         for (int i = 0; i < this.getSize(); i++)
            if (this.get(i).getName().equals(name)) return this.get(i);
         return new Function().register().setName(name);
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
     
     
     @Override
     public Function newT(String s) {
         return new Function().parse(s);
     }
     
     
     /**
      * palutetaan alkutilanteeseen
      */
     @Override
     public void resetAltered() {
         this.altered = false;
     }
     
     
     @Override
     public void setNextID(int id) {
         Function.setNextFid(id);
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
